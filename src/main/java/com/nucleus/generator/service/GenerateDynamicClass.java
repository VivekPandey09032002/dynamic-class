package com.nucleus.generator.service;


import com.nucleus.generator.bean.Field;
import com.nucleus.generator.entity.CustomMethod;
import com.nucleus.generator.repo.CustomMethodRepo;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenerateDynamicClass {

    @Value("${template.packageName}")
    private String packageName;

    @Value("${template.modelName}")
    private String modelName;

    @Value("${template.save.location}")
    private String saveLocation;


    private final VelocityContext velocityContext;
    private final Template velocityTemplate;

    private final CustomMethodRepo customMethodRepo;

    public String generateClass() {
        velocityContext.put("packageName", packageName);


        List<Field> properties = new ArrayList<>();
        properties.add(new Field("id", "int"));
        properties.add(new Field("firstName", "String"));
        properties.add(new Field("lastName", "String"));
        properties.add(new Field("dob", "LocalDate"));

        // get dynamic methods  TODO TESTING PURPOSE
        List<CustomMethod> customMethods = new ArrayList<CustomMethod>();
        CustomMethod customMethod = new CustomMethod();
        customMethod.setMethodDescription("public void validate(){ //validate code}");
        customMethod.setMethodName("validate");
        customMethodRepo.save(customMethod);
        Optional<CustomMethod> validate = customMethodRepo.findById("validate");
        customMethods.add(validate.get());

        velocityContext.put("customMethods", customMethods);


        // other properties
        velocityContext.put("className", modelName);
        velocityContext.put("properties", properties);



        StringWriter writer = new StringWriter();
        velocityTemplate.merge(velocityContext, writer);

        System.out.println(writer.toString());

        return writer.toString();
    }

    public void writeToPackage() throws IOException {
        String generatedContent = generateClass();
        File file = new File(saveLocation +modelName + ".java");
        FileWriter writer = new FileWriter(file);
        writer.write(generatedContent);
        writer.close();
    }
}