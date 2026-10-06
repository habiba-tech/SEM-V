from sklearn import datasets 
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score, classification_report , confusion_matrix

iris = datasets.load_iris()
X = iris.data
Y = iris.target

X_train , X_test ,Y_train , Y_test = train_test_split(X,Y, test_size=0.21, random_state=44)

scaler = StandardScaler()
X_train , X_test = scaler.fit_transform(X_train), scaler.transform(X_test)

model = SVC(kernel='linear',C=1.0)
model.fit(X_train,Y_train)

Y_pred = model.predict(X_test)

accuracy = accuracy_score(Y_test,Y_pred)*100
report = classification_report(Y_test,Y_pred , target_names=iris.target_names)
matrix = confusion_matrix(Y_test,Y_pred)

print(f"Accuracy : {accuracy}%")
print("Classification_Report :", report)
print("Confusion_matrix : ", matrix)
