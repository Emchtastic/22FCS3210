'''
CS3210 - Principles of Programming Languages - Fall 2022
Instructor: Thyago Mota
Description: Homework 06 - An iterator for a BST (Binary Search Tree)
Student Name: Alex Emch
'''

class BST: 

    # the labels for the nodes are obtained from a list of strings
    def __init__(self, labels): 
        if (not isinstance(labels, list)):
            raise Exception("labels must be a list!")
        if (not labels):
            raise Exception("labels must not be empty!")
        self.label = labels[0] # first label makes the root node 
        self.left = None
        self.right = None        
        for label in labels[1:]:
            BST._add(self, label)

    # adds a node with the given label to the BST using recursion
    def _add(node, label):
        if (label < node.label):
            if (node.left):
                BST._add(node.left, label)
            else:
                node.left = BST([label])
        else:
            if (node.right):
                BST._add(node.right, label)
            else:
                node.right = BST([label])

    # helper method that builds a string representation of the BST using recursion
    def _print(node, tabs = ""): 
        out = ""
        if (node):
            out += tabs + node.label + "\n"
            if node.left:
                out += BST._print(node.left, tabs + "   ")
            if node.right:
                out += BST._print(node.right, tabs + "   ")                
        return out

    def __str__(self):
        return BST._print(self, "")

    def in_order(node):
        if (node):
            yield from BST.in_order(node.left)
            yield node.label
            yield from BST.in_order(node.right)


tree = BST(["b", "a", "d", "c", "e"])
print("Tree:")
print(tree)
newTree = [n for n in BST.in_order(tree)]
for n in newTree:
    print(n, end = ' ')

