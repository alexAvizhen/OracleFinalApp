package com.netcracker.avizhen.persistence.ui;

import com.netcracker.avizhen.persistence.entity.Group;
import com.netcracker.avizhen.persistence.entity.Program;
import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.entity.UsersStudying;
import com.netcracker.avizhen.persistence.factory.ActionListenerCreator;
import com.netcracker.avizhen.persistence.utils.Actions;
import com.netcracker.avizhen.persistence.utils.CurrentPresentationTable;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Александр on 18.12.2017.
 */
public class MainFrame extends JFrame{
    private ApplicationContext context;
    private CurrentPresentationTable currentPresentationTable = new CurrentPresentationTable();

    private JTable presentationTable = new JTable();

    private JButton userButton = new JButton("User table");
    private JButton groupsButton = new JButton("Groups table");
    private JButton programButton = new JButton("Programs table");
    private JButton usersStudyingButton = new JButton("Users Studying table");
    private JButton firstSelectButton = new JButton("First select");
    private JButton secondSelectButton = new JButton("Second select");

    private JButton addEntityButton = new JButton("Add entity");
    private JButton updateEntityButton = new JButton("Update entity");
    private JButton deleteEntityButton = new JButton("Delete entity");


    public MainFrame(String title, ApplicationContext context) throws HeadlessException {
        this.context = context;
        setTitle(title);
        JPanel managePanel = new JPanel(new FlowLayout());
        managePanel.add(userButton);
        managePanel.add(groupsButton);
        managePanel.add(programButton);
        managePanel.add(usersStudyingButton);
        managePanel.add(firstSelectButton);
        managePanel.add(secondSelectButton);
        addListenersToManageButtons();
        add(managePanel, BorderLayout.NORTH);

        JScrollPane pane = new JScrollPane(presentationTable);
        add(pane, BorderLayout.CENTER);

        JPanel bottomManagePanel = new JPanel(new FlowLayout());
        bottomManagePanel.add(addEntityButton);
        bottomManagePanel.add(updateEntityButton);
        bottomManagePanel.add(deleteEntityButton);
        addListenersToBottomManageButtons();
        add(bottomManagePanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setVisible(true);
    }

    private void addListenersToBottomManageButtons() {
        ActionListenerCreator listenerCreator = new ActionListenerCreator(context, presentationTable);
        addEntityButton.addActionListener(listenerCreator.createActionListener(Actions.ADD, currentPresentationTable));
        updateEntityButton.addActionListener(listenerCreator.createActionListener(Actions.UPDATE, currentPresentationTable));
        deleteEntityButton.addActionListener(listenerCreator.createActionListener(Actions.DELETE, currentPresentationTable));
    }

    private void addListenersToManageButtons() {
        ActionListenerCreator listenerCreator = new ActionListenerCreator(context, presentationTable);
        userButton.addActionListener(listenerCreator.createActionListener(User.class, currentPresentationTable));
        groupsButton.addActionListener(listenerCreator.createActionListener(Group.class, currentPresentationTable));
        programButton.addActionListener(listenerCreator.createActionListener(Program.class, currentPresentationTable));
        usersStudyingButton.addActionListener(listenerCreator.createActionListener(UsersStudying.class, currentPresentationTable));
        firstSelectButton.addActionListener(listenerCreator.createActionListener(Actions.FIRST_SELECT, null));
        secondSelectButton.addActionListener(listenerCreator.createActionListener(Actions.SECOND_SELECT, null));
    }
}
