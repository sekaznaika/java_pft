package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public ContactData contactById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id = '" + id + "'").list();
        session.getTransaction().commit();
        session.close();
        return result.get(0);
    }

    public GroupData groupById(int groupId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData where group_id = '" + groupId + "'").list();
        session.getTransaction().commit();

        return result.get(0);
    }

    public Contacts contactsInGroup(int groupId) {
        Contacts contactsInGroup = new Contacts();
        Contacts allContactsInGroup = groupById(groupId).getContacts();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (ContactData contact : allContactsInGroup) {
            List<ContactData> contactList = session.createQuery("from ContactData where id = '" + contact.getId()
                    + "' and deprecated = '0000-00-00'").list();
            if (contactList.size() == 1) {
                contactsInGroup.add(contactList.get(0));
            }
        }
        session.getTransaction().commit();
        session.close();
        return contactsInGroup;
    }

}