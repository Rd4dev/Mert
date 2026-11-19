from langchain_community.document_loaders import TextLoader
from langchain_ollama import OllamaEmbeddings
from langchain_chroma import Chroma
from langchain_text_splitters import RecursiveCharacterTextSplitter

def create_vector_store():
    loader = TextLoader("knowledge/damage_policies.txt")
    documents = loader.load()

    splitter = RecursiveCharacterTextSplitter(
        chunk_size=500,
        chunk_overlap=50
    )

    chunks = splitter.split_documents(documents)

    embeddings = OllamaEmbeddings(model="nomic-embed-text")
    vector_store = Chroma.from_documents(
        documents = chunks,
        embedding = embeddings
    )

    return vector_store

def retrieve_policy(vector_store, query):
    results = vector_store.similarity_search(
        query,
        k=2
    )

    return results

def build_policy_context(policies):
    return "\n\n".join(
        policy.page_content for policy in policies
    )