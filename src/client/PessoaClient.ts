import { Pessoa } from '@/model/PessoaModel';
import axios, { AxiosInstance } from 'axios';
class PessoaClient {

    private axiosClient: AxiosInstance

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/pessoa',
            headers: { 'Content-type': 'application/json' }
        });
    }

    public async findById(id: number): Promise<Pessoa> {
        try {
            return (await this.axiosClient.get<Pessoa>(`/${id}`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async listaAll(): Promise<Pessoa[]> {
        try {
            return (await this.axiosClient.get<Pessoa[]>(`/lista`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async cadastrar(pessoa: Pessoa): Promise<string> {
        try {
            return (await this.axiosClient.post<string>(``, pessoa)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }
    public async editar(id: number, pessoa: Pessoa): Promise<string> {
        try {
            return (await this.axiosClient.put<string>(`/${id}`, pessoa)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }
    public async excluir(id: number): Promise<string> {
        try {
            return (await this.axiosClient.delete<string>(`/${id}`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }
}

export default new PessoaClient();