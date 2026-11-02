def encrypt(text, shift):
    result = " "
    alphabet = "ABCDEFGHIJKLMNOPRQSTUVWXYZ"
    for ch in text  :
        if ch in alphabet : 
            index = alphabet.index(ch)
            new_index = (index + shift ) % 26
            result += alphabet[new_index] 
        else:
            result += ch 
    return result

def decrypt(text , shift):
    result = " "
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for ch in text :
        if ch in alphabet:
            index = alphabet.index(ch)
            new_index = (index - shift) % 26
            result += alphabet[new_index]
        else : 
            result += ch
    return result

messsage = input("Enter a Meassage : ").upper()
shift = int(input("Enter a shift key : "))
cipher = encrypt(messsage , shift)
plain = decrypt(cipher , shift)

print("Encypted : ", cipher)
print("Decrypted : ", cipher)

    
