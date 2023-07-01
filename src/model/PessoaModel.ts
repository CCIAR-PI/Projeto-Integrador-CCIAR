import { Escolaridade } from "./EscolaridadeModel";
import { Sexo } from "./SexoModel";

export class Pessoa {
    id!: number
    nome!: string
    cpf!: string
    dataNascimento!: number;
    rg!: string;
    telefoneEmergencia!: string;
    naturalidade!: string;
    nacionalidade!: string;
    escolaridade!: Escolaridade;
    sexo!: Sexo;
    rua!: string;
    bairro!: string;
    numeroCasa!: number;
    cep!: string;
    cadastroPor!: string;
    dataCadastro!: string;
    edicaoCadastro!: string;
    ativo!: boolean;

}