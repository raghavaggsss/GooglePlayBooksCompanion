try:
    from xml.etree.cElementTree import XML
except ImportError:
    from xml.etree.ElementTree import XML
import zipfile

path = "PlayBooks/"

WORD_NAMESPACE = '{http://schemas.openxmlformats.org/wordprocessingml/2006/main}'
PARA = WORD_NAMESPACE + 'p'
TEXT = WORD_NAMESPACE + 't'


def get_docx_text(path):
    """
    Take the path of a docx file as argument, return the text in unicode.
    """
    document = zipfile.ZipFile(path)
    xml_content = document.read('word/document.xml')
    document.close()
    tree = XML(xml_content)

    paragraphs = []
    for paragraph in tree.getiterator(PARA):
        texts = [node.text
                 for node in paragraph.getiterator(TEXT)
                 if node.text]
        if texts:
            paragraphs.append(''.join(texts))

    return '\n\n'.join(paragraphs)


import sys
name_doc = sys.argv[1]
name_txt = name_doc.split('.')[0] + ".txt"
dump = get_docx_text(path + name_doc)

import re
s = re.compile(r'\n[a-zA-Z]+\n')
s1 = s.findall(dump)

words = list(map(lambda x: x[1:][:-1], s1))


with open(path + name_txt, 'w') as outfile:
    for word in words:
        outfile.write(word)
        outfile.write('\n')
