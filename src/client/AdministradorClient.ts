import { Administrador } from '@/model/AdministradorModel';
import axios, { AxiosInstance } from 'axios';
class AdministradorClient {

    private axiosClient: AxiosInstance

    constructor() {
        this.axiosClient = axios.create({
            baseURL: 'http://localhost:8080/api/administrador',
            headers: { 'Content-type': 'application/json' }
        });
    }

    public async findById(id: number): Promise<Administrador> {
        try {
            return (await this.axiosClient.get<Administrador>(`/${id}`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async listaAll(): Promise<Administrador[]> {
        try {
            return (await this.axiosClient.get<Administrador[]>(`/lista`)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }

    public async cadastrar(administrador: Administrador): Promise<string> {
        try {
            return (await this.axiosClient.post<string>(``, administrador)).data
        } catch (error: any) {
            return Promise.reject(error.response.data)
        }
    }
    public async editar(id: number, administrador: Administrador): Promise<string> {
        try {
            return (await this.axiosClient.put<string>(`/${id}`, administrador)).data
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

export default new AdministradorClient();