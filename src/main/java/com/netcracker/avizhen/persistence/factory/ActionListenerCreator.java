package com.netcracker.avizhen.persistence.factory;

import com.netcracker.avizhen.persistence.entity.Group;
import com.netcracker.avizhen.persistence.entity.Program;
import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.entity.UsersStudying;
import com.netcracker.avizhen.persistence.repository.GroupRepository;
import com.netcracker.avizhen.persistence.repository.ProgramRepository;
import com.netcracker.avizhen.persistence.repository.UserRepository;
import com.netcracker.avizhen.persistence.repository.UsersStudyingRepository;
import com.netcracker.avizhen.persistence.ui.UpdateDialog;
import com.netcracker.avizhen.persistence.utils.Actions;
import com.netcracker.avizhen.persistence.utils.CurrentPresentationTable;
import com.netcracker.avizhen.persistence.utils.CustomTableModel;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Александр on 18.12.2017.
 */
public class ActionListenerCreator {
    private ApplicationContext context;
    private JTable presentationTable;

    public ActionListenerCreator(ApplicationContext context, JTable presentationTable) {
        this.context = context;
        this.presentationTable = presentationTable;
    }

    public <T> ActionListener createActionListener(final Class<T> beanClass, final CurrentPresentationTable currentPresentationTable) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = beanClass.getName();
                TableModel tableModel = null;
                if (Group.class.getName().equals(className)) {
                    GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
                    tableModel = TableModelCreator.createTableModel(Group.class, groupRepository.getAll());
                    currentPresentationTable.setCurrentPresentationEntity(Group.class);
                } else if (Program.class.getName().equals(className)) {
                    ProgramRepository programRepository = (ProgramRepository) context.getBean("programRepository");
                    tableModel = TableModelCreator.createTableModel(Program.class, programRepository.getAll());
                    currentPresentationTable.setCurrentPresentationEntity(Program.class);
                } else if (User.class.getName().equals(className)) {
                    UserRepository userRepository = (UserRepository) context.getBean("userRepository");
                    tableModel = TableModelCreator.createTableModel(User.class, userRepository.getAll());
                    currentPresentationTable.setCurrentPresentationEntity(User.class);
                } else if (UsersStudying.class.getName().equals(className)) {
                    UsersStudyingRepository usersStudyingRepository = (UsersStudyingRepository) context.getBean("usersStudyingRepository");
                    tableModel = TableModelCreator.createTableModel(UsersStudying.class, usersStudyingRepository.getAll());
                    currentPresentationTable.setCurrentPresentationEntity(UsersStudying.class);
                }
                if (tableModel != null) {
                    presentationTable.setModel(tableModel);
                    ((AbstractTableModel) tableModel).fireTableDataChanged();
                }

            }
        };

    };

    public <T> ActionListener createActionListener(final Actions action, final CurrentPresentationTable currentPresentationTable) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Actions.ADD.equals(action)) {
                    TableModel model = presentationTable.getModel();
                    if (model != null && model instanceof  CustomTableModel) {
                        CustomTableModel customModel = (CustomTableModel) model;
                        try {
                            customModel.getList().add(currentPresentationTable.getCurrentPresentationEntity().newInstance());
                        } catch (InstantiationException | IllegalAccessException e1) {
                            JOptionPane.showMessageDialog(null, "Not expected error");
                        }
                    }
                } else if (Actions.UPDATE.equals(action)) {
                    int index = presentationTable.getSelectedRow();
                    if (index != -1) {
                        Object entity = ((CustomTableModel) presentationTable.getModel()).getList().get(index);
                        new UpdateDialog(null, "Update dialog", context, entity, currentPresentationTable.getCurrentPresentationEntity());
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select row");
                    }
                } else if (Actions.DELETE.equals(action)) {
                    int index = presentationTable.getSelectedRow();
                    if (index != -1) {
                        Object entity = ((CustomTableModel) presentationTable.getModel()).getList().get(index);
                        String currentEntityClassname = currentPresentationTable.getCurrentPresentationEntity().getName();
                        if (User.class.getName().equals(currentEntityClassname)) {
                            User user = ((User) entity);
                            UserRepository userRepository = (UserRepository) context.getBean("userRepository");
                            if (user.getId() != null) {
                                userRepository.delete(user.getId());
                            }
                        } else if (Program.class.getName().equals(currentEntityClassname)) {
                            Program program = ((Program) entity);
                            ProgramRepository programRepository = (ProgramRepository) context.getBean("programRepository");
                            if (program.getId() != null) {
                                programRepository.delete(program.getId());
                            }
                        } else if (Group.class.getName().equals(currentEntityClassname)) {
                            Group group = ((Group) entity);
                            GroupRepository groupRepository = (GroupRepository) context.getBean("groupRepository");
                            if (group.getId() != null) {
                                groupRepository.delete(group.getId());
                            }
                        } else if (UsersStudying.class.getName().equals(currentEntityClassname)) {
                            UsersStudying usersStudying = ((UsersStudying) entity);
                            UsersStudyingRepository usersStudyingRepository = (UsersStudyingRepository) context.getBean("usersStudyingRepository");
                            if (usersStudying.getId() != null) {
                                usersStudyingRepository.delete(usersStudying.getId());
                            }
                        }

                        ((CustomTableModel) presentationTable.getModel()).getList().remove(index);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select row");
                    }
                } else if (Actions.FIRST_SELECT.equals(action)) {
                    String courseName = JOptionPane.showInputDialog("Input course name");
                    if (courseName != null) {
                        Integer courseId;
                        if ("Матрос".equals(courseName)) {
                            courseId = 1;
                        } else if ("Шкипер".equals(courseName)) {
                            courseId = 2;
                        } else if ("Капитан".equals(courseName)) {
                            courseId = 3;
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect course name");
                            courseId = 0;
                        }
                        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
                        TableModel tableModel = TableModelCreator.createTableModel(User.class, userRepository.findByCurrentCourseId(courseId));
                        if (tableModel != null) {
                            presentationTable.setModel(tableModel);
                            ((AbstractTableModel) tableModel).fireTableDataChanged();
                        }
                    }
                } else if (Actions.SECOND_SELECT.equals(action)) {
                    UserRepository userRepository = (UserRepository) context.getBean("userRepository");
                    TableModel tableModel = TableModelCreator.createTableModel(User.class, userRepository.findUsersStudyingInGreece());
                    if (tableModel != null) {
                        presentationTable.setModel(tableModel);
                        ((AbstractTableModel) tableModel).fireTableDataChanged();
                    }
                }
                if ( presentationTable.getModel() != null &&  presentationTable.getModel() instanceof CustomTableModel) {
                    ((CustomTableModel) presentationTable.getModel()).fireTableDataChanged();
                }
            }
        };

    }
}
