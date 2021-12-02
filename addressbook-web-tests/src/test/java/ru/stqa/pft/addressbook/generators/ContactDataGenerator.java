package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

        @Parameter(names = "-c", description = "Contact count")
        public int count;

        @Parameter(names = "-f", description = "Target file")
        public String file;

        @Parameter(names = "-d", description = "Data format")
        public String format;

        public static void main(String[] args) throws IOException {
            ru.stqa.pft.addressbook.generators.ContactDataGenerator generator = new ru.stqa.pft.addressbook.generators.ContactDataGenerator();
            JCommander jCommander = new JCommander(generator);
            try {
                jCommander.parse(args);
            } catch (ParameterException ex) {
                jCommander.usage();
                return;
            }
            generator.run();
        }

        private void run() throws IOException {
            List<ContactData> groups = generateContacts(count);
            if (format.equals("json")) {
                saveAsJson(groups, new File(file));
            } else {
                System.out.println("Unrecognized format " + format);
            }
        }

        private List<ContactData> generateContacts(int count) {
            List<ContactData> contacts = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                contacts.add(new ContactData().withFirstname(String.format("Test1 %s", i)).withLastname(String.format("Test2 %s", i))
                        .withHomePhone(String.format("333%s", i)).withMobilePhone(String.format("222%s", i)).withWorkPhone(String.format("111%s", i))
                        .withEmail(String.format("Test3", i)));
            }
            return contacts;

        }

        private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(contacts);
            Writer writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        }

    }

