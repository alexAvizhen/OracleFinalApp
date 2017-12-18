package com.netcracker.avizhen.persistence.ui;

import com.netcracker.avizhen.persistence.entity.Program;
import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.repository.ProgramRepository;
import com.netcracker.avizhen.persistence.repository.UserRepository;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Александр on 18.12.2017.
 */
public class UpdateDialog extends JDialog implements ActionListener {
    private Map<String, JTextField> propertyToField = new HashMap<>();
    private ApplicationContext context;
    private Object entity;
    private Class currentPresentationEntity;

    public UpdateDialog(Frame owner, String title,  ApplicationContext context, Object entity, Class currentPresentationEntity) {
        super(owner, title, true);
        this.context = context;
        this.entity = entity;
        this.currentPresentationEntity = currentPresentationEntity;

        JPanel contentPanel = new JPanel();
        propertyToField.clear();
        if (User.class.getName().equals(currentPresentationEntity.getName())) {
            User user = (User) entity;
            contentPanel.setLayout(new GridLayout(4, 2));
            contentPanel.add(new JLabel("Id"));
            JTextField tempField = new JTextField(user.getId() == null ? "" : user.getId().toString());
            propertyToField.put("id", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Name"));
            tempField = new JTextField(user.getName());
            propertyToField.put("name", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Surname"));
            tempField = new JTextField(user.getSurname());
            propertyToField.put("surname", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Current course id"));
            tempField = new JTextField(user.getCurrentCourseId() == null ? "" : user.getCurrentCourseId().toString());
            propertyToField.put("current_course_id", tempField);
            contentPanel.add(tempField);
        } else if (Program.class.getName().equals(currentPresentationEntity.getName())) {
            Program program = (Program) entity;
            contentPanel.setLayout(new GridLayout(4, 2));

            contentPanel.add(new JLabel("Id"));
            JTextField tempField = new JTextField(program.getId() == null ? "" : program.getId().toString());
            propertyToField.put("id", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Name"));
            tempField = new JTextField(program.getName());
            propertyToField.put("name", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Duration"));
            tempField = new JTextField(program.getDuration() == null ? "" : program.getDuration().toString());
            propertyToField.put("duration", tempField);
            contentPanel.add(tempField);

            contentPanel.add(new JLabel("Course id"));
            tempField = new JTextField(program.getCourseId() == null ? "" : program.getCourseId().toString());
            propertyToField.put("course_id", tempField);
            contentPanel.add(tempField);
        } else {
            contentPanel.add(new JLabel("Update for this table don't implemented"));
        }

        getContentPane().add(contentPanel, BorderLayout.CENTER);


        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Update");
        buttonPane.add(button);
        button.addActionListener(this);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (User.class.getName().equals(currentPresentationEntity.getName())) {
            User user = ((User) entity);
            user.setId(Integer.valueOf(propertyToField.get("id").getText()));
            user.setName(propertyToField.get("name").getText());
            user.setSurname(propertyToField.get("surname").getText());
            user.setCurrentCourseId(Integer.valueOf(propertyToField.get("current_course_id").getText()));
            UserRepository userRepository = (UserRepository) context.getBean("userRepository");
            if (user.getId() == null) {
                JOptionPane.showMessageDialog(null, "Id cannot be null");
            } else {
                userRepository.save(user);
            }
        } else if (Program.class.getName().equals(currentPresentationEntity.getName())) {
            Program program = ((Program) entity);
            program.setId(Integer.valueOf(propertyToField.get("id").getText()));
            program.setName(propertyToField.get("name").getText());
            program.setDuration(Integer.valueOf(propertyToField.get("duration").getText()));
            program.setCourseId(Integer.valueOf(propertyToField.get("course_id").getText()));
            if (program.getId() == null) {
                JOptionPane.showMessageDialog(null, "Id cannot be null");
            } else {
                ProgramRepository programRepository = (ProgramRepository) context.getBean("programRepository");
                programRepository.save(program);
            }
        }
        setVisible(false);
        dispose();

    }
}
