package br.com.fncit.recommender;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import javax.swing.*;

public class RecommenderApp {


    public static void main(String[] args) throws IOException {
        FileDataModel model = getModel();
        remocommender(model);
    }

    private static void remocommender(FileDataModel model){
        Evaluator evaluator = new Evaluator();
        try {
            Double errorMargin = evaluator.evaluateRecommender(model);
            if(errorMargin == 1.0){
                System.out.println("deu ruim");
                return;
            }if(errorMargin > 0.4){
                System.out.println("Margem de erro muito alto");
                // return;
            }
            Recommender recommender = new RecommenderProductsBuilder().buildRecommender(model);
            recommend(recommender);
        }catch(TasteException e1) {
            System.out.println("Taste Exception");
            e1.printStackTrace();
        }

    }
    
    private static void recommend(Recommender recommender) throws TasteException {
        String choice;
        choice = JOptionPane.showInputDialog("Digite o userId (Digite 'a' para parar)");

        while (!choice.equals("a")) {
            int choic = Integer.parseInt(choice);
            List<RecommendedItem> recommendations = recommender.recommend(choic, 3);

            JOptionPane.showMessageDialog(null, recommendations);

            choice = JOptionPane.showInputDialog("Digite o userId, (Digite 'a' para parar)");
        }
    }

    private static FileDataModel getModel(){
        FileDataModel model = null;
        String choice = JOptionPane.showInputDialog("Escolha 1 para Produto, 2 para Cursos");
        try {
            int choic = Integer.parseInt(choice);
            if(choic == 1){
                System.out.println("Model Produtos");
                model = new Recommende().getProductsModel();
            }else if( choic == 2){
                System.out.println("Model Cursos");
                model = new Recommende().getCourseModel();
            }else {
                System.out.println("Model default");
                model = new Recommende().getProductsModel();
            }
        }catch (IOException e){
            System.out.println("Io Exception");
            e.printStackTrace();
        }finally {
            return model;
        }
    }

}
