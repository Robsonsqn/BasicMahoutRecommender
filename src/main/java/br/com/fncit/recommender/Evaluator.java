package br.com.fncit.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.common.RandomUtils;


public class Evaluator {

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
}
