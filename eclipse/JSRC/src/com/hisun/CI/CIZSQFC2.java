package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQFC2 {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    DBParm CITACR_RD;
    brParm CITCNT_BR = new brParm();
    brParm CITADR_BR = new brParm();
    DBParm CITRELN_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    short WS_II = 0;
    String WS_CI_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRFDM CIRFDM = new CIRFDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CIRACR CIRACR = new CIRACR();
    CIRRELN CIRRELN = new CIRRELN();
    CICFB13 CICFB13 = new CICFB13();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQFC2 CICSQFC2;
    public void MP(SCCGWA SCCGWA, CICSQFC2 CICSQFC2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQFC2 = CICSQFC2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQFC2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQFC2.RC);
        IBS.init(SCCGWA, CICFB13);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        B030_INQ_PER_BAS_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        if (CICSQFC2.DATA.CI_NO.trim().length() > 0) {
            WS_CI_NO = CICSQFC2.DATA.CI_NO;
        } else if (CICSQFC2.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSQFC2.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
            }
            WS_CI_NO = CIRACR.CI_NO;
        } else if (CICSQFC2.DATA.ID_TYPE.trim().length() > 0 
                && CICSQFC2.DATA.ID_NO.trim().length() > 0 
                && CICSQFC2.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSQFC2.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSQFC2.DATA.ID_NO;
            CIRBAS.CI_NM = CICSQFC2.DATA.CI_NM;
            BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
            T000_READ_CITBAS_BY_IDNM();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "NOT FOUND");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
            }
            WS_CI_NO = CIRBAS.KEY.CI_NO;
        } else {
            CEP.TRC(SCCGWA, "LACK OF NORMAL INFORMATION");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT);
        }
    }
    public void B030_INQ_PER_BAS_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1' 
            || (CIRBAS.CI_ATTR != '1' 
            && CIRBAS.CI_ATTR != '6')) {
            CEP.TRC(SCCGWA, "BAS INF NOT FND");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
        if (CIRBAS.CI_TYP != '3') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITFDM();
        if (pgmRtn) return;
        R000_TRANS_FDM_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICFB13.DATA.ID_REMARK = CIRID.REMARK;
        CICFB13.DATA.ID_EXPDT = CIRID.EXP_DT;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB13.DATA.CI_ENM = CIRNAM.CI_NM;
        }
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITCNT();
        if (pgmRtn) return;
        T000_READNEXT_CITCNT();
        if (pgmRtn) return;
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_I += 1) {
            R000_TRANS_CNT_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITCNT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITCNT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRADR);
        CIRADR.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_STARTBR_CITADR();
        if (pgmRtn) return;
        T000_READNEXT_CITADR();
        if (pgmRtn) return;
        for (WS_II = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1'; WS_II += 1) {
            R000_TRANS_ADR_DATA_TO_OUTPUT();
            if (pgmRtn) return;
            T000_READNEXT_CITADR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITADR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRRELN.KEY.CIREL_CD = "20101";
        T000_READ_CITRELN_BY_RELCD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB13.DATA.LEG_ID_TYPE = CIRRELN.KEY.RCI_IDTYP;
            CICFB13.DATA.LEG_ID_NO = CIRRELN.KEY.RCI_IDNO;
            CICFB13.DATA.LEG_NAME = CIRRELN.KEY.RCI_NAME;
        }
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRRELN.KEY.CIREL_CD = "10504";
        T000_READ_CITRELN_BY_RELCD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB13.DATA.CTS_ID_TYPE = CIRRELN.KEY.RCI_IDTYP;
            CICFB13.DATA.CTS_ID_NO = CIRRELN.KEY.RCI_IDNO;
            CICFB13.DATA.CTS_NAME = CIRRELN.KEY.RCI_NAME;
        }
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB13.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICFB13.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICFB13.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICFB13.DATA.CI_STSW = CIRBAS.STSW;
        CICFB13.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICFB13.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICFB13.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICFB13.DATA.ID_NO = CIRBAS.ID_NO;
        CICFB13.DATA.OIC_NO = CIRBAS.OIC_NO;
    }
    public void R000_TRANS_FDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB13.DATA.CI_SUB_TYP = CIRFDM.CI_SUB_TYP;
        CICFB13.DATA.RESIDENT = CIRFDM.RESIDENT;
        CICFB13.DATA.ORG_TYPE = CIRFDM.ORG_TYPE;
        CICFB13.DATA.FIN_TYPE = CIRFDM.FIN_TYPE;
        CICFB13.DATA.ECO = CIRFDM.ECO;
        CICFB13.DATA.HECO = CIRFDM.HECO;
        CICFB13.DATA.INDUS = CIRFDM.INDUS1;
        CICFB13.DATA.INDUS2 = CIRFDM.INDUS2;
        CEP.TRC(SCCGWA, CICFB13.DATA.INDUS2);
        CICFB13.DATA.SID_FLG = CIRFDM.SID_FLG;
        CICFB13.DATA.COMP_SIZE = CIRFDM.COMP_SIZE;
        CICFB13.DATA.BUSN_SCP = CIRFDM.BUSN_SCP;
        CICFB13.DATA.REG_DT = CIRFDM.REG_DT;
        CICFB13.DATA.REG_CCY = CIRFDM.REG_CCY;
        CICFB13.DATA.REG_AMT = CIRFDM.REG_AMT;
        CICFB13.DATA.HCNTY = CIRFDM.HCNTY;
        CICFB13.DATA.REMARK = CIRFDM.REMARK;
        CICFB13.DATA.EMP_NUM = CIRFDM.EMP_NUM;
        CICFB13.DATA.CAP_AMT = CIRFDM.CAP_AMT;
        CICFB13.DATA.CAP_CCY = CIRFDM.CAP_CCY;
        CICFB13.DATA.COR_NO = CIRFDM.COR_NO;
        CEP.TRC(SCCGWA, CICFB13.DATA.COR_NO);
        CICFB13.DATA.OPER_INC = CIRFDM.OPER_INC;
        CICFB13.DATA.REVENUE = CIRFDM.REVENUE;
        CICFB13.DATA.TOTAL_ASS = CIRFDM.TOTAL_ASS;
        CICFB13.DATA.SUP_NM = CIRFDM.SUP_NM;
        CICFB13.DATA.SUP_ORG_TYPE = CIRFDM.SUP_ORG_NO;
        CICFB13.DATA.SUP_AP_NO = CIRFDM.SUP_AP_NO;
        CICFB13.DATA.PB_BANK = CIRFDM.PB_BANK;
        CICFB13.DATA.PB_AC_NO = CIRFDM.PB_AC_NO;
        CICFB13.DATA.CONT1_NM = CIRFDM.CONT1_NM;
        CICFB13.DATA.CONT1_MOB_NO = CIRFDM.CONT1_MOB_NO;
        CICFB13.DATA.CONT1_TEL_NO = CIRFDM.CONT1_TEL_NO;
        CICFB13.DATA.CONT2_NM = CIRFDM.CONT2_NM;
        CICFB13.DATA.CONT2_MOB_NO = CIRFDM.CONT2_MOB_NO;
        CICFB13.DATA.CONT2_TEL_NO = CIRFDM.CONT2_TEL_NO;
        CICFB13.DATA.FIN_DIR_NM = CIRFDM.FIN_DIR_NM;
        CICFB13.DATA.FIN_DIR_MOB_NO = CIRFDM.FIN_DIR_MOB_NO;
        CICFB13.DATA.FIN_DIR_TEL_NO = CIRFDM.FIN_DIR_TEL_NO;
    }
    public void R000_TRANS_CNT_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("10")) {
            CICFB13.DATA.CONT_NO = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("13")) {
            CICFB13.DATA.FAX = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("30")) {
            CICFB13.DATA.EMAIL = CIRCNT.CNT_INFO;
        } else {
        }
    }
    public void R000_TRANS_ADR_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("210")) {
            CICFB13.DATA.REG_CNTY_CD = CIRADR.CNTY_CD;
            CICFB13.DATA.REG_ADR_NM = CIRADR.ADR_NM;
            CICFB13.DATA.REG_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("220")) {
            CICFB13.DATA.OFFI_CNTY_CD = CIRADR.CNTY_CD;
            CICFB13.DATA.OFFI_ADR_NM = CIRADR.ADR_NM;
            CICFB13.DATA.OFFI_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("270")) {
            CICFB13.DATA.STMT_CNTY_CD = CIRADR.CNTY_CD;
            CICFB13.DATA.STMT_ADR_NM = CIRADR.ADR_NM;
            CICFB13.DATA.STMT_POST_CD = CIRADR.POST_CD;
        } else {
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIB13";
        SCCFMT.DATA_PTR = CICFB13;
        SCCFMT.DATA_LEN = 4407;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.READ(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_READ_CITBAS_BY_IDNM() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_STARTBR_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_BR.rp = new DBParm();
        CITCNT_BR.rp.TableName = "CITCNT";
        CITCNT_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRCNT, CITCNT_BR);
    }
    public void T000_READNEXT_CITCNT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCNT, this, CITCNT_BR);
    }
    public void T000_ENDBR_CITCNT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCNT_BR);
    }
    public void T000_STARTBR_CITADR() throws IOException,SQLException,Exception {
        CITADR_BR.rp = new DBParm();
        CITADR_BR.rp.TableName = "CITADR";
        CITADR_BR.rp.eqWhere = "CI_NO";
        IBS.STARTBR(SCCGWA, CIRADR, CITADR_BR);
    }
    public void T000_READNEXT_CITADR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRADR, this, CITADR_BR);
    }
    public void T000_ENDBR_CITADR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITADR_BR);
    }
    public void T000_READ_CITRELN_BY_RELCD() throws IOException,SQLException,Exception {
        CITRELN_RD = new DBParm();
        CITRELN_RD.TableName = "CITRELN";
        CITRELN_RD.eqWhere = "CI_NO,CIREL_CD";
        CITRELN_RD.fst = true;
        IBS.READ(SCCGWA, CIRRELN, CITRELN_RD);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
