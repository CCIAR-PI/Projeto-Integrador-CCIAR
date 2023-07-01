import { Atividade } from '@/model/AtividadeModel';
import axios, { AxiosInstance } from 'axios';
class AtividadeClient {

    private axiosClient: AxiosInstance

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/atividade',
            headers: { 'Content-type': 'application/json' }
        });
    }

    public async findById(id: number): Promise<Atividade> {
        try {
            return (await this.axiosClient.get<Atividade>(`/${id}`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async listaAll(): Promise<Atividade[]> {
        try {
            return (await this.axiosClient.get<Atividade[]>(`/lista`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async cadastrar(atividade: Atividade): Promise<string> {
        try {
            return (await this.axiosClient.post<string>(``, atividade)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }
    public async editar(id: number, atividade: Atividade): Promise<string> {
        try {
            return (await this.axiosClient.put<string>(`/${id}`, atividade)).data
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

export default new AtividadeClient();