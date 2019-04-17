package br.com.fncit.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.common.RandomUtils;

import java.io.IOException;


public class Evaluator {


    public static void main(String[] args) {
        evaluateRecommender();
    }

    public Double evaluateRecommender (FileDataModel model){
        try {
            RandomUtils.useTestSeed();

            RecommenderBuilder builder = new RecommenderProductsBuilder();

            RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

            double error = evaluator.evaluate(builder, null, model, 0.9, 1.0);
            System.out.println("Margem de erro = "+ error);
            return error;
        }catch(TasteException e1) {
            e1.printStackTrace();
        }
        return 1.0;
    }

    public static void evaluateRecommender(){
        try {
            FileDataModel model = new Recommende().getProductsModel();
            RandomUtils.useTestSeed();

            RecommenderBuilder builder = new RecommenderProductsBuilder();

            RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

            double error = evaluator.evaluate(builder, null, model, 0.9, 1.0);
            System.out.println("Margem de erro = "+ error);
        }catch (IOException e){
            System.out.println("Io Exception");
            e.printStackTrace();
        }catch(TasteException e1) {
            System.out.println("Taste Exception");
            e1.printStackTrace();
        }
    }
}
