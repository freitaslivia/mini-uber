package br.com.fiap.jadv.rm99892.checkpoint.enums;

public enum SituacaoCorrida {
    AGUARDANDO("A"),
    EM_ANDAMENTO("E"),
    CONCLUIDA("C"),
    CANCELADA("X");
    
    private String codigo;
  
  private SituacaoCorrida(String codigo) {
      this.codigo = codigo;
  }
  
  public String getCodigo() {
      return codigo;
  }
  
  public static SituacaoCorrida getCorridaPorCodigo(String codigo) {
      for (SituacaoCorrida tipo : SituacaoCorrida.values()) {
          if (tipo.getCodigo().equals(codigo)) {
              return tipo;
          }
      }
      return null;
  }

}
