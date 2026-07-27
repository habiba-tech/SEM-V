from pandas.core.arrays import categorical
import pandas as pd
import matplotlib.pyplot as plt

from sklearn.preprocessing import LabelEncoder
from sklearn.preprocessing import StandardScaler

from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

from sklearn.metrics import accuracy_score
from sklearn.metrics import confusion_matrix
from sklearn.metrics import classification_report

df = pd.read_csv("online_shoppers_intention.csv")

print("First Five Records")
print(df.head())

print("\nDataset Information")
print(df.info())

print("\nShape")
print(df.shape)

print("\nColumns")
print(df.columns.tolist())

encoder = LabelEncoder()
Categorical_Columns = [
  "Month",
  "VisitorType",
  "Weekend"
]

for column in Categorical_Columns:
  df[column] = encoder.fit_transform(df[column])

df["Revenue"] = encoder.fit_transform(df["Revenue"])

print("\nEncoded Dataset")
print(df.head())

x = df.drop("Revenue", axis=1)
y = df["Revenue"]

scaler = StandardScaler()
x = scaler.fit_transform(x)

x_train, x_test, y_train, y_test = train_test_split(
    x, y, test_size=0.2,random_state=42)

print("\nTraining Shape")
print(x_train.shape)

print("\nTesting Shape")
print(x_test.shape)

knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(x_train, y_train)

print("\nModel Trained Succesessfully")
y_pred = knn.predict(x_test)

result= pd.DataFrame({
    "Actual":y_test.values,
    "Predicted":y_test
})

print("\nFirst 20 Predictions")
print(result.head(20))

accuracy = accuracy_score(y_test, y_pred)

print("\nAccuracy = ",round(accuracy * 100,2),"%")

cm = confusion_matrix(y_test, y_pred)
print("\nConfusion Matrix")
print(cm)

cr = classification_report(y_test, y_pred)
print("\nClassification Report")
print(cr)

plt.figure(figsize=(6, 4))
df["Revenue"].value_counts().plot(kind="bar")
plt.title("Revenue Distribution")
plt.xlabel("Revenue")
plt.ylabel("Count")
plt.show()

accuracy_list = []
k_values = range(1,20)

for k in k_values:
  model = KNeighborsClassifier(n_neighbors=k)
  model.fit(x_train, y_train)
  prediction = model.predict(x_test)
  accuracy_list.append(accuracy_score(y_test, prediction))

# Moved plot outside the loop
plt.figure(figsize=(8,5))
plt.plot(k_values, accuracy_list, marker="o") # Changed marker from "0" to "o"
plt.xlabel("K Values")
plt.ylabel("Accuracy")
plt.title("Accuracy for different K Values")
plt.grid(True)
plt.show()

new_customer_data = x_test[50].reshape(1, -1) # Reshape for single sample prediction



prediction = knn.predict(new_customer_data)

print("\nPrediction for New Customer")
if prediction[0] == 1:
  print("Customer is likely to Generate Revenue")
else:
  print("Customer is not likely to Generate Revenue")

print("\nProgram completed Successfully")
