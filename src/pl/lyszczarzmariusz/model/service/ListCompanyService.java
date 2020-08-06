package pl.lyszczarzmariusz.model.service;

import pl.lyszczarzmariusz.model.RecruitmentModel;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class ListCompanyService {
    //private static final File listCompanyFile = new File("src\\pl\\lyszczarzmariusz\\ListCompany.txt");
    private static final File listCompanyFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Recruitment_Information\\ListCompany.txt");
    private static final List<RecruitmentModel> list = new LinkedList<>();

    public static List<RecruitmentModel> readListFromFile() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(listCompanyFile.getPath()));

        String number = fileReader.readLine();
        if (number != null) {

            int numberOfRecords = Integer.parseInt(number);

            for (int i = 0; i < numberOfRecords; i++) {
                list.add(createRecruitmentModel(fileReader.readLine()));
            }
        }

        fileReader.close();

        return list;
    }

    private static RecruitmentModel createRecruitmentModel(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
        RecruitmentModel recruitmentModel = new RecruitmentModel();

        recruitmentModel.setNameCompany(stringTokenizer.nextToken());
        recruitmentModel.setOfferURL(stringTokenizer.nextToken());
        recruitmentModel.setWorkPlace(stringTokenizer.nextToken());
        recruitmentModel.setApplicationDate(LocalDate.of(Integer.parseInt(stringTokenizer.nextToken()),
                Integer.parseInt(stringTokenizer.nextToken()),
                Integer.parseInt(stringTokenizer.nextToken())));

        return recruitmentModel;
    }

    public static void saveRecord(RecruitmentModel recruitmentModel) {
        list.add(recruitmentModel);
    }

    public static void writeListToFile() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(listCompanyFile.getPath()));
        fileWriter.write("" + list.size());
        for (int i = 0; i < list.size(); i++) {
            fileWriter.newLine();
            fileWriter.append(createStringTokenizer(list.get(i)));
        }
        fileWriter.close();
    }

    private static String createStringTokenizer(RecruitmentModel recruitmentModel){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(recruitmentModel.getNameCompany());
        stringBuilder.append("|");
        stringBuilder.append(recruitmentModel.getOfferURL());
        stringBuilder.append("|");
        stringBuilder.append(recruitmentModel.getWorkPlace());
        stringBuilder.append("|");
        stringBuilder.append(recruitmentModel.getApplicationDate().getYear());
        stringBuilder.append("|");
        stringBuilder.append(recruitmentModel.getApplicationDate().getMonthValue());
        stringBuilder.append("|");
        stringBuilder.append(recruitmentModel.getApplicationDate().getDayOfMonth());

        return stringBuilder.toString();
    }

    public static List<RecruitmentModel> getList() {
        return list;
    }

    public static void existConfigurationFolder() throws IOException {
        File documentFolder = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Recruitment_Information");
        if (!documentFolder.exists()) {
            documentFolder.mkdir();
        }
        File listFile = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\Recruitment_Information\\ListCompany.txt");
        if (!listFile.exists()) {
            listFile.createNewFile();
        }
    }
}
