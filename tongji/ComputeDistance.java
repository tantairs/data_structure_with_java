package com.lianxi.tongji;

import java.io.*;

public class ComputeDistance {
   
	public static void main(String[] args) {
//      convertFile();
       dataDealWithPearson();
       dataDealWithMinkowski();
       //dataDealWithEuclidean_RowBased();
       //dataDealWithPearson_RowBased();
   }

  public static void dataDealWithMinkowski(){
        String[] title = {};
        float[] number ={};
        int countRow =1;
        double sum = 0;
        int coloum =0;
        float[][] fullData = new float[100000][230];
        float addSum = 0;
        String result = null;

        try {
            String encoding = "utf8";
            File file = new File("C:/My Documents/Tongji/Joint_Research_Fred/BioData/Dataset_20160330/Treutlein2014-RawMatrix.txt");
            File file1 = new File("C:/My Documents/Tongji/Joint_Research_Fred/BioData/Dataset_20160330/Treutlein2014-RawMatrix.IN");
            //File file2 = new File("/Users/tantairs/Downloads/Dueck2015-log1p-deseqnorm-scaled.txt");

            if(file.isFile()&&file.exists()){
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file),encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                /*InputStreamReader reader2 = new InputStreamReader(
                        new FileInputStream(file2),encoding
                );
                BufferedReader bufferedReader2 = new BufferedReader(reader2);*/
                String line;
                title = bufferedReader.readLine().split("\t");
                coloum = title.length ;
                number = new float[coloum+1];
                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null){
                    String [] tempColoum = line.split("\t");
                    
                    sum = 0;
                    for(int i = 1; i < coloum; i++){
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                            sum += number[i];
                        } catch (NumberFormatException e){
                            e.printStackTrace();
                        }
                    }
                    
                    /*for(int i = 1; i <= coloum; i++){

                        sum += number[i];
                    }*/

                    if (sum > coloum / 2) {					//we will keep this gene
	                    countRow++;
	                    for(int i = 1; i < coloum; i++){
	                        fullData[countRow][i] = number[i];
	                    }
                    }
                }
            }

            System.out.println("一共的基因个数(有效): " + (countRow-1));


            FileWriter fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((coloum - 1)  + "   " + (coloum - 1) + "   " + "999");
            bufferedWriter.newLine();
            for(int i = 1; i < coloum; i++){
                for(int j = i+1; j < coloum; j++){
                    for(int z = 1; z < countRow; z++){
                        addSum += Math.pow(Math.log(fullData[z][j] + 1) - Math.log(fullData[z][i] + 1),2);
                    }
                    result = title[i] + "   " + title[j] +  "   " + Math.sqrt(addSum);
                    addSum=0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void dataDealWithPearson(){
        String[] title = {};
        float[] number = {};
        int countRow =1;
        double sum = 0;
        int coloum =0;
        float[][] fullData = new float[100000][200];
        double a = 0,b = 0,c = 0;
        double[] average = new double[200];
        String result = null;

        try {
            String encoding = "utf8";
            File inputFile = new File("C:/My Documents/Tongji/Joint_Research_Fred/BioData/Dataset_20160330/Treutlein2014-RawMatrix.txt");
            File outputFile = new File("C:/My Documents/Tongji/Joint_Research_Fred/BioData/Dataset_20160330/Treutlein2014-RawMatrix_Pearson.IN");
            File file2 = new File("/Users/tantairs/Downloads/Dueck2015-log1p-deseqnorm-scaled.txt"); //used for the title



            if(inputFile.isFile() && inputFile.exists()){
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(inputFile),encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);

                /*InputStreamReader reader2 = new InputStreamReader(
                        new FileInputStream(file2),encoding
                );
                BufferedReader bufferedReader2 = new BufferedReader(reader2);*/

                String line;
                //title = bufferedReader2.readLine().split("\t");
                title = bufferedReader.readLine().split("\t");
                coloum = title.length ;
                number = new float[coloum+1];

                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null){

                    String [] tempColoum = line.split("\t");
                    sum = 0;
                    for(int i = 1; i < coloum; i++)	{			//we don't consider the first column
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                            sum += number[i];
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }
                    }
                    /*for(int i = 1; i < coloum; i++) {

                        sum += number[i];
                    }*/

                    if (sum > coloum / 2) {					//we will keep this gene
                    	countRow++;
                    
                    	for	(int i = 1; i < coloum; i++)	{
                    		fullData[countRow][i] = number[i];
                    	}
                    }


                }
            }

            System.out.println( "一共的基因个数(有效): " + (countRow-1));


            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((coloum - 1) + "   " + (coloum - 1) + "   " + "999");
            bufferedWriter.newLine();

            for(int i = 1; i < coloum; i++){
                double sumtemp = 0.0;
                for(int j = 1; j < countRow; j++){
                    sumtemp += fullData[j][i];
                }
                average[i] = sumtemp/(countRow-1);
            }

            for(int i = 1; i < coloum - 1; i++){
                for(int j = i + 1; j < coloum; j++){
                    for(int z = 1; z < countRow; z++){
                        a += ((fullData[z][i]) - average[i]) * (fullData[z][j] -average[j]);
                        b += Math.pow((fullData[z][i]) - average[i],2);
                        c += Math.pow((fullData[z][j]) - average[j],2);
                    }
                    result = title[i] + "   " + title[j] +  "   " + (1-a /(Math.sqrt(b) * Math.sqrt(c)));
                    a = b = c = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public static void dataDealWithEuclidean_RowBased() {
        String[] title = {};
        //float[] number = {};
        int countRow =1;
        int coloum =0;
        float[][] fullData = new float[200][10];			//we use hard-coded stuff here
        double a = 0,b = 0,c = 0;
        double[] average = new double[100];
        String [] names = new String[100];
        String result = null;

        try {
            String encoding = "utf8";
            File inputFile = new File("C:/My Documents/Tongji/Joint_Research_Fred/Wine_Data/Wine_data_CleanUp_Input.txt");
            File outputFile = new File("C:/My Documents/Tongji/Joint_Research_Fred/Wine_Data/Wine_dataLog_Euclidean_Input.IN");


            if(inputFile.isFile() && inputFile.exists()){
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(inputFile),encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);

                /*InputStreamReader reader2 = new InputStreamReader(
                        new FileInputStream(file2),encoding
                );
                BufferedReader bufferedReader2 = new BufferedReader(reader2);*/

                String line;
                //title = bufferedReader2.readLine().split("\t");
                title = bufferedReader.readLine().split("\t");
                coloum = title.length ;
                
                
                //number = new float[200];			//hard coded

//                bufferedReader.readLine();
                while ((line = bufferedReader.readLine()) != null && countRow < 50){

                    String [] tempColoum = line.split("\t");
                    names[countRow] = tempColoum[0];				//store the name
                    for(int i = 1; i < coloum; i++)	{			//we don't consider the first column
                        try {
                        	fullData[countRow][i] = (float)Math.log(Float.parseFloat(tempColoum[i]) + 1);
                            
                        } catch (NumberFormatException e){
                            e.printStackTrace();
                        }
                    }
 
                    countRow++;

                }
            }

            System.out.println( "一共的对象(有效): " + (countRow-1));

            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((countRow-1) + "   " + (countRow-1) + "   " + "999");
            bufferedWriter.newLine();
            
            //Euclidean distance
            for( int i = 1; i < countRow - 1; i++) {
            	
                for(int j = i + 1; j < countRow; j++) {
                	
                	float addSum = 0;
                    for (int z = 1; z < coloum; z++){
                        addSum += Math.pow(fullData[i][z] - fullData[j][z], 2);
                    }
                    result = names[i] + "   " + names[j] +  "   " + Math.sqrt(addSum);
                   
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            /*

            for(int i = 1; i < coloum; i++){
                double sumtemp = 0.0;
                for(int j = 1; j < countRow; j++){
                    sumtemp += fullData[j][i];
                }
                average[i] = sumtemp/(countRow-1);
            }

            for(int i = 1; i < coloum - 1; i++){
                for(int j = i + 1; j < coloum; j++){
                    for(int z = 1; z < countRow; z++){
                        a += ((fullData[z][i]) - average[i]) * (fullData[z][j] -average[j]);
                        b += Math.pow((fullData[z][i]) - average[i],2);
                        c += Math.pow((fullData[z][j]) - average[j],2);
                    }
                    result = title[i] + "   " + title[j] +  "   " + (1-a /(Math.sqrt(b) * Math.sqrt(c)));
                    a = b = c = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }*/

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public static void dataDealWithPearson_RowBased() {

        String[] title = new String[100];
        float[] number = {};
        int countRow = 1;
        int coloum = 0;
        float[][] fullData = new float[100][10];
        double a = 0, b = 0, c = 0;
        double[] average = new double[100];
        String result = null;

        try {
            String encoding = "utf8";
            File file = new File("C:/My Documents/Tongji/Joint_Research_Fred/Wine_Data/Wine_data_CleanUp_Input.txt");
            File file1 = new File("C:/My Documents/Tongji/Joint_Research_Fred/Wine_Data/Wine_dataLog_Pearson_Input.IN");

            if (file.isFile() && file.exists()) {
                InputStreamReader reader = new InputStreamReader(
                        new FileInputStream(file), encoding
                );
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                coloum = bufferedReader.readLine().split("\t").length;
                number = new float[coloum];


                while ((line = bufferedReader.readLine()) != null && countRow < 50) {

                    String[] tempColoum = line.split("\t");

                    title[countRow] = tempColoum[0];
                    for (int i = 1; i < coloum; i++) {
                        try {
                            number[i] = Float.parseFloat(tempColoum[i]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }


                    for (int i = 1; i < coloum; i++) {
                        fullData[countRow][i] = (float) Math.log((double)number[i] + 1);
                    }

                    countRow++;

                }
            }


            FileWriter fileWriter = new FileWriter(file1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("CP12.IN");
            bufferedWriter.newLine();
            bufferedWriter.write((countRow-1) + "   " + (countRow-1) + "   " + "999");
            bufferedWriter.newLine();

            //求每一个对象要素的平均值
            for (int i = 1; i < countRow; i++) {
                double sumtemp = 0.0;
                for (int j = 1; j < coloum; j++) {
                    sumtemp += fullData[i][j];
                }
                average[i] = sumtemp / (coloum-1);
            }

            for (int i = 1; i < countRow-1; i++) {
                for (int j = i + 1; j < countRow; j++) {
                    for (int z = 1; z < coloum; z++) {
                        a += ((fullData[i][z]) - average[i]) * (fullData[j][z] - average[j]);
                        b += Math.pow((fullData[i][z]) - average[i], 2);
                        c += Math.pow((fullData[j][z]) - average[j], 2);
                    }
                    result = title[i] + "   " + title[j] + "   " +(1- a / (Math.sqrt(b) * Math.sqrt(c)));
                    a = b = c = 0;
                    bufferedWriter.write(result);
                    bufferedWriter.newLine();
                }

            }

            bufferedWriter.write("-999");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void convertFile( ){
       String encoding = "utf8";
       File file = new File("/Users/tantairs/Downloads/Dueck2015-RawMatrix.txt");
       File file1 = new File("/Users/tantairs/Downloads/convertedFile.txt");
       try{
           if(file.isFile()&&file.exists()){
               InputStreamReader reader = new InputStreamReader(
                       new FileInputStream(file),encoding
               );
               FileWriter fileWriter = new FileWriter(file1);
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               BufferedReader bufferedReader = new BufferedReader(reader);
               String line;
               while ((line = bufferedReader.readLine()) != null){
                   bufferedWriter.write(line.replaceAll("\t",", "));
                   bufferedWriter.newLine();
               }
           }
       }catch (Exception e){
           e.printStackTrace();
       }

   }
}
