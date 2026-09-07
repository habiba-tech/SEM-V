import pandas as pd
from mlxtend.frequent_patterns import apriori,association_rules
from mlxtend.preprocessing import TransactionEncoder

#Sample transcation 
transactions = [
    ['milk','bread','butter'],
    ['milk','bread'],
    ['milk','butter'],
    ['bread','butter'],
    ['milk','bread','butter']
]

#One-hot encode the transcations
te = TransactionEncoder()
te_ary = te.fit(transactions).transform(transactions)
df = pd.DataFrame(te_ary,columns=te.columns_)

#Genreate frequent itemsets
frequent_itemsets = apriori(df, min_support=0.6,use_colnames=True)

#Generate frequent itemsets
rules = association_rules(frequent_itemsets,metric="confidence",min_threshold=0.7)

#Print the results 
print("Frequent Itemsets:")
print(frequent_itemsets)

print('\nAssociation Rules:')
print(rules[['antecedents','consequents','support','confidence']])
