def encrypt(text,shift):
    result = ""

    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for ch in text:
        if ch in alphabet:
            index = alphabet.index(ch)
            new_index = (index + shift) % 26
            result += alphabet[new_index]

        else:
            result += ch
    return result

def decrypt(text,shift):
    result = ""
    
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for ch in text:
        if ch in alphabet:
            index = alphabet.index(ch)
            new_index = (index - shift) % 26
            result += alphabet[new_index]
    
        else:
            result += ch
    return result

message = input("Enter message: ").upper()
shift = int(input("Enter shift: "))
cipher = encrypt(message, shift)

cipher=encrypt(message,shift)
print("Encrypted:",cipher)

plain=decrypt(cipher,shift)
print("Decrypted:",cipher)
