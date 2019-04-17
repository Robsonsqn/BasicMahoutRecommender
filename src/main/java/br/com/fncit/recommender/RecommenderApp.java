package br.com.fncit.recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class RecommenderApp {

    public static void main(String args[]){
        Evaluator evaluator = new Evaluator();
        try {
            File file = new File("dados.csv");
            FileDataModel model = new FileDataModel(file);
            Double errorMargin = evaluator.evaluateRecommender(model);
            if(errorMargin > 0.4){
                System.out.println("Margem de erro muito alto");
                // return;
            }
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            recommend(recommender);
        }catch (IOException e){
            System.out.println("Io Exception");
            e.printStackTrace();
        }catch(TasteException e1) {
            System.out.println("Taste Exception");
            e1.printStackTrace();
        }
    }
    
    public static void recommend(UserBasedRecommender recommender) throws TasteException {
	    List<RecommendedItem> recommendations = recommender.recommend(2, 3);
	    for (RecommendedItem recommendation : recommendations) {
	      System.out.println(recommendation);
	    }
    }
}
