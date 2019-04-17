package br.com.fncit.recommender;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import java.io.File;
import java.io.IOException;

public class Recommende {

    public FileDataModel getProductsModel() throws IOException {
       File file = new File("src/main/resources/dados.csv");
       return new FileDataModel(file);
    }

    public FileDataModel getCourseModel() throws IOException {
        File file = new File("src/main/resources/cursos_com_questionarios.csv");
        return new FileDataModel(file);
    }

}
