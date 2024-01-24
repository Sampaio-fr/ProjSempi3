CREATE OR REPLACE FUNCTION fncUS209ListarporEstado(estado pedido.estado_entrega%type) RETURN SYS_REFCURSOR AS
    lista Sys_Refcursor;
BEGIN
    OPEN lista FOR SELECT * FROM pedido WHERE estado_entrega = estado;
    return lista;
end;

/

CREATE OR REPLACE FUNCTION fncUS209ListarporData RETURN SYS_REFCURSOR AS
    lista Sys_Refcursor;
BEGIN
    OPEN lista FOR SELECT * FROM pedido ORDER BY data;
    return lista;
end;

/

CREATE OR REPLACE FUNCTION fncUS209ListarporCliente(cliente pedido.codigo_interno%type) RETURN SYS_REFCURSOR AS
    lista Sys_Refcursor;
BEGIN
    OPEN lista FOR SELECT * FROM pedido WHERE codigo_interno = cliente;
    return lista;
end;

/

CREATE OR REPLACE FUNCTION fncUS209ListarporID(ID pedido.ID_pedido%type) RETURN SYS_REFCURSOR AS
    lista Sys_Refcursor;
BEGIN
    OPEN lista FOR SELECT * FROM pedido WHERE id_pedido = ID;
    return lista;
end;

/

CREATE OR REPLACE FUNCTION fncUS209ListarporPreco RETURN SYS_REFCURSOR AS
    lista Sys_Refcursor;
BEGIN
    OPEN lista FOR SELECT * FROM pedido ORDER BY preco;
    return lista;
end;