package contacts.serialization;

import contacts.contact.Contacts;

import java.io.*;

public class ContactsSerializationUtils {
    public  String filename;

    public ContactsSerializationUtils(String filename) {
        this.filename = filename;
    }
    public Contacts deserializeContact() throws IOException, ClassNotFoundException {
        if (filename != null) {
            File file = new File(filename);
            if (file.exists()) {
                Contacts contacts = deserialize();
                return contacts;
            }
        }
        return null;
    }

    public void serializeContact(Contacts contacts) throws IOException {
        if (filename == null) {
            return;
        }
        this.serialize(contacts);
    }
    public  void serialize(Contacts obj) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public Contacts deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return (Contacts) obj;
    }


}
