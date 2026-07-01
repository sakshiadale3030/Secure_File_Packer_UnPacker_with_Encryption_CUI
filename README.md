# 🔐 Secure File Packer-Unpacker with Custom Key-Based ASCII Encryption

A Java-based **Secure File Packer and Unpacker** application that combines multiple files into a single packed file while providing **custom key-based ASCII encryption** for enhanced security. During unpacking, files are automatically decrypted using either a manually entered key or a saved key file.

---

## 📌 Features

- 📁 Pack multiple files from a directory into a single file.
- 🔒 Encrypt file contents using a custom key-based ASCII encryption algorithm.
- 🔑 Save the encryption key in a separate `.key` file with an additional ASCII shift.
- 📂 Unpack files with:
  - Manual decryption key.
  - Automatic key loading from the generated `.key` file.
- 📊 Display packing and unpacking statistics.
- 💻 Console-based user interface (CUI).
- ⚡ Lightweight and easy to use.

---

## 🛠 Technologies Used

- Java
- Object-Oriented Programming (OOP)
- File Handling
- Streams (FileInputStream & FileOutputStream)
- Exception Handling
- Custom ASCII Encryption Algorithm

---

## 📂 Project Structure

```
Secure_File_Packer_UnPacker_with_Encryption_CUI/
│
├── Main.java
├── Packer.java
├── Unpacker.java
└── README.md
```

---

## 🚀 How It Works

### Packing Process

1. Enter the name of the packed file.
2. Enter the directory containing files.
3. Enter a custom encryption key.
4. The application:
   - Reads every file.
   - Creates a 100-byte header containing filename and filesize.
   - Encrypts file data using the custom key.
   - Stores everything inside one packed file.
   - Saves the encrypted key into a `.key` file.

---

### Unpacking Process

There are two options:

#### Option 1: Manual Key

- Enter the packed file name.
- Enter the original encryption key.
- Files are decrypted and restored.

#### Option 2: Automatic Key

- Enter only the packed file name.
- The application automatically loads the key from the `.key` file.
- Files are decrypted automatically.

---

## 🔒 Encryption Algorithm

### Data Encryption

Each byte is encrypted using the following formula:

```
Encrypted Byte = (Original Byte + (Key Character + 3)) % 256
```

The key characters are repeatedly used in a cyclic manner until all file bytes are encrypted.

---

### Data Decryption

```
Decrypted Byte = (Encrypted Byte - (Key Character - 3) + 256) % 256
```

---

### Key Storage

The encryption key is not stored directly.

Each character is shifted by **+3 ASCII values** before saving.

Example:

```
Original Key : hello

Stored Key   : khoor
```

During unpacking, each character is shifted back by **-3** to retrieve the original key.

---

## 📋 Menu

```
1. Pack Files (with encryption)

2. Unpack Files (manual key)

3. Unpack Files (auto load key)

4. Exit
```

---

## 📊 Sample Output

### Packing

```
Packed file gets successfully created.

Encryption key saved successfully.

Packing Activity Done...

Total Files Packed : 5

All files have been encrypted using custom key-based ASCII shifting.
```

---

### Unpacking

```
Successfully accessed packed file...

Decryption key loaded successfully.

File unpacked successfully.

Total Files Unpacked : 5

All files have been decrypted using custom key-based ASCII shifting.
```

---

## 📖 Classes

### Main.java

- Displays menu
- Accepts user input
- Calls packing/unpacking operations

---

### PackerX.java

Responsible for:

- Reading directory files
- Creating packed file
- Creating headers
- Encrypting file data
- Saving encryption key
- Generating statistical report

---

### UnpackerX.java

Responsible for:

- Reading packed file
- Loading decryption key
- Decrypting file data
- Restoring original files
- Generating statistical report

---

## 📈 Advantages

- Secure file storage using custom encryption.
- Easy backup of multiple files.
- Automatic key management.
- Fast packing and unpacking.
- Simple command-line interface.
- Platform independent (Java).

---

## 🚧 Future Enhancements

- GUI version using Java Swing/JavaFX.
- AES or RSA encryption support.
- Password masking while entering keys.
- Compression before encryption.
- Folder hierarchy preservation.
- Multi-threaded packing.
- File integrity verification using SHA-256.
- Progress bar for large files.

---

## 👩‍💻 Author

**Sakshi Ashok Adale**

Date - **01/07/2026**
