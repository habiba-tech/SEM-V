from sklearn import datasets
from sklearn.model_selection import train_test_split 
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score,classification_report,confusion_matrix

iris = datasets.load_iris()
X= iris.data
Y =iris.target

X_train, X_test , Y_train , Y_test = train_test_split(X ,Y , test_size=0.21 , random_state= 44)

scaler = StandardScaler()
X_train ,X_test = scaler.fit_transform(X_train) , scaler.transform(X_test)

k=4 
knn = KNeighborsClassifier(n_neighbors= k)
knn.fit(X_train,Y_train)

y_pred = knn.predict(X_test)

accuracy = accuracy_score(y_pred ,Y_test)*100 
report = classification_report(y_pred , Y_test , target_names = iris.target_names)
matrix = confusion_matrix(y_pred , Y_test)

print(f"Accuracy : {accuracy}")
print("Classification Report : " , report)
print("Confusion Matrix : ", matrix)
