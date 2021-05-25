# Room reservation system

1. Zadání práce:
 Udělejte v Javě program, který bude simulovat rezervaci hotelového pokoje. Program by měl obsahovat hlavní class, který bude obsahovat UI, které bude fungovat, dokud uživatel nebude chtít ukončit program. Program umožňuje zaregistrovat se nebo se přihlásit pomocí již existujícího emailu a hesla. Zkontrolovat rezervaci, nebo vytvořit novou. Vstupy by měly být čteny z konzole od uživatele. Pro uložení emailu a hesla použijte textové soubory.

2. Funkční specifikace![as](https://user-images.githubusercontent.com/79978940/119535420-4376e780-bd88-11eb-9900-c39bb2e5d201.jpg)
2.1 Class diagram ![App](https://user-images.githubusercontent.com/79978940/119535539-673a2d80-bd88-11eb-8810-f650ef688f30.png)
3. Externí knihovna: 
Používám knihovnu Java SMTP s autentizací TLS. Chcete-li konfigurovat, budete muset použít třídu Properties a nastavit tam SMTP host, TLS port,enable authentication, enable STARTTLS. Dále musíte vytvořit objekt Session s autentizací podle přihlašovacího jména a hesla. Pak v objektu message vytvořit téma zprávy a zprávy samotné. Prostřednictvím třídy Transport pošlete svou zprávu

Tests:
 ![0](https://user-images.githubusercontent.com/79978940/119540563-db2b0480-bd8d-11eb-97fa-9da3a6c2ad22.jpg)
1. ![111](https://user-images.githubusercontent.com/79978940/119540580-de25f500-bd8d-11eb-9bd7-b132c4172b12.jpg)
2. ![222](https://user-images.githubusercontent.com/79978940/119540586-dfefb880-bd8d-11eb-8d1b-08cca1931f49.jpg)
3. ![33](https://user-images.githubusercontent.com/79978940/119540591-e120e580-bd8d-11eb-9e69-9193b611807a.jpg)
4. ![4](https://user-images.githubusercontent.com/79978940/119540597-e2521280-bd8d-11eb-9d5e-d36112055397.jpg)
5. ![5](https://user-images.githubusercontent.com/79978940/119540602-e3833f80-bd8d-11eb-94d4-854e66ea2171.jpg)

