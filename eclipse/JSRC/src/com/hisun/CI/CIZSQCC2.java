package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSQCC2 {
    int BAS_CI_NM_LEN;
    int JIBS_tmp_int;
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
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
    CIRCDM CIRCDM = new CIRCDM();
    CIRID CIRID = new CIRID();
    CIRNAM CIRNAM = new CIRNAM();
    CIRCNT CIRCNT = new CIRCNT();
    CIRADR CIRADR = new CIRADR();
    CIRACR CIRACR = new CIRACR();
    CIRRELN CIRRELN = new CIRRELN();
    CICFB12 CICFB12 = new CICFB12();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSQCC2 CICSQCC2;
    public void MP(SCCGWA SCCGWA, CICSQCC2 CICSQCC2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSQCC2 = CICSQCC2;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSQCC2 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSQCC2.RC);
        IBS.init(SCCGWA, CICFB12);
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
        if (CICSQCC2.DATA.CI_NO.trim().length() > 0) {
            WS_CI_NO = CICSQCC2.DATA.CI_NO;
        } else if (CICSQCC2.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSQCC2.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND);
            }
            WS_CI_NO = CIRACR.CI_NO;
        } else if (CICSQCC2.DATA.ID_TYPE.trim().length() > 0 
                && CICSQCC2.DATA.ID_NO.trim().length() > 0 
                && CICSQCC2.DATA.CI_NM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.ID_TYPE = CICSQCC2.DATA.ID_TYPE;
            CIRBAS.ID_NO = CICSQCC2.DATA.ID_NO;
            CIRBAS.CI_NM = CICSQCC2.DATA.CI_NM;
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
        if (CIRBAS.CI_TYP != '2') {
            CEP.TRC(SCCGWA, "CI TYP ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TYP_INPUT_ERR);
        }
        R000_TRANS_BAS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        T000_READ_CITCDM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        R000_TRANS_CDM_DATA_TO_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRID.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        T000_READ_CITID();
        if (pgmRtn) return;
        CICFB12.DATA.ID_REMARK = CIRID.REMARK;
        CICFB12.DATA.ID_EXPDT = CIRID.EXP_DT;
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRNAM.KEY.NM_TYPE = "03";
        T000_READ_CITNAM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB12.DATA.CI_ENM = CIRNAM.CI_NM;
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
            CICFB12.DATA.LEG_ID_TYPE = CIRRELN.KEY.RCI_IDTYP;
            CICFB12.DATA.LEG_ID_NO = CIRRELN.KEY.RCI_IDNO;
            CICFB12.DATA.LEG_NAME = CIRRELN.KEY.RCI_NAME;
        }
        IBS.init(SCCGWA, CIRRELN);
        CIRRELN.KEY.CI_NO = CIRBAS.KEY.CI_NO;
        CIRRELN.KEY.CIREL_CD = "10504";
        T000_READ_CITRELN_BY_RELCD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CICFB12.DATA.CTS_ID_TYPE = CIRRELN.KEY.RCI_IDTYP;
            CICFB12.DATA.CTS_ID_NO = CIRRELN.KEY.RCI_IDNO;
            CICFB12.DATA.CTS_NAME = CIRRELN.KEY.RCI_NAME;
        }
        R000_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_BAS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB12.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICFB12.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICFB12.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICFB12.DATA.CI_STSW = CIRBAS.STSW;
        CICFB12.DATA.IDE_STSW = CIRBAS.IDE_STSW;
        if (CIRBAS.CI_NM == null) CIRBAS.CI_NM = "";
        JIBS_tmp_int = CIRBAS.CI_NM.length();
        for (int i=0;i<252-JIBS_tmp_int;i++) CIRBAS.CI_NM += " ";
        CICFB12.DATA.CI_NM = CIRBAS.CI_NM.substring(0, BAS_CI_NM_LEN);
        CICFB12.DATA.ID_TYPE = CIRBAS.ID_TYPE;
        CICFB12.DATA.ID_NO = CIRBAS.ID_NO;
        CICFB12.DATA.OIC_NO = CIRBAS.OIC_NO;
    }
    public void R000_TRANS_CDM_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        CICFB12.DATA.CI_SUB_TYP = CIRCDM.CI_SUB_TYP;
        CICFB12.DATA.RESIDENT = CIRCDM.RESIDENT;
        CICFB12.DATA.ORG_TYPE = CIRCDM.ORG_TYPE;
        CICFB12.DATA.ECO = CIRCDM.ECO;
        CICFB12.DATA.HECO = CIRCDM.HECO;
        CICFB12.DATA.INDUS = CIRCDM.INDUS1;
        CICFB12.DATA.INDUS2 = CIRCDM.INDUS2;
        CEP.TRC(SCCGWA, CICFB12.DATA.INDUS2);
        CICFB12.DATA.SID_FLG = CIRCDM.SID_FLG;
        CICFB12.DATA.COMP_SIZE = CIRCDM.COMP_SIZE;
        CICFB12.DATA.BUSN_SCP = CIRCDM.BUSN_SCP;
        CICFB12.DATA.REG_DT = CIRCDM.REG_DT;
        CICFB12.DATA.REG_CCY = CIRCDM.REG_CCY;
        CICFB12.DATA.REG_AMT = CIRCDM.REG_AMT;
        CICFB12.DATA.HCNTY = CIRCDM.HCNTY;
        CICFB12.DATA.REMARK = CIRCDM.REMARK;
        CICFB12.DATA.EMP_NUM = CIRCDM.EMP_NUM;
        CICFB12.DATA.CAP_AMT = CIRCDM.CAP_AMT;
        CICFB12.DATA.CAP_CCY = CIRCDM.CAP_CCY;
        CICFB12.DATA.OPER_INC = CIRCDM.OPER_INC;
        CICFB12.DATA.REVENUE = CIRCDM.REVENUE;
        CICFB12.DATA.TOTAL_ASS = CIRCDM.TOTAL_ASS;
        CICFB12.DATA.SUP_NM = CIRCDM.SUP_NM;
        CICFB12.DATA.SUP_ORG_TYPE = CIRCDM.SUP_ORG_NO;
        CICFB12.DATA.SUP_AP_NO = CIRCDM.SUP_AP_NO;
        CICFB12.DATA.PB_BANK = CIRCDM.PB_BANK;
        CICFB12.DATA.PB_AC_NO = CIRCDM.PB_AC_NO;
        CICFB12.DATA.CONT1_NM = CIRCDM.CONT1_NM;
        CICFB12.DATA.CONT1_MOB_NO = CIRCDM.CONT1_MOB_NO;
        CICFB12.DATA.CONT1_TEL_NO = CIRCDM.CONT1_TEL_NO;
        CICFB12.DATA.CONT2_NM = CIRCDM.CONT2_NM;
        CICFB12.DATA.CONT2_MOB_NO = CIRCDM.CONT2_MOB_NO;
        CICFB12.DATA.CONT2_TEL_NO = CIRCDM.CONT2_TEL_NO;
        CICFB12.DATA.FIN_DIR_NM = CIRCDM.FIN_DIR_NM;
        CICFB12.DATA.FIN_DIR_MOB_NO = CIRCDM.FIN_DIR_MOB_NO;
        CICFB12.DATA.FIN_DIR_TEL_NO = CIRCDM.FIN_DIR_TEL_NO;
    }
    public void R000_TRANS_CNT_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("10")) {
            CICFB12.DATA.CONT_NO = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("13")) {
            CICFB12.DATA.FAX = CIRCNT.TEL_NO;
        } else if (CIRCNT.KEY.CNT_TYPE.equalsIgnoreCase("30")) {
            CICFB12.DATA.EMAIL = CIRCNT.CNT_INFO;
        } else {
        }
    }
    public void R000_TRANS_ADR_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("210")) {
            CICFB12.DATA.REG_CNTY_CD = CIRADR.CNTY_CD;
            CICFB12.DATA.REG_ADR_NM = CIRADR.ADR_NM;
            CICFB12.DATA.REG_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("220")) {
            CICFB12.DATA.OFFI_CNTY_CD = CIRADR.CNTY_CD;
            CICFB12.DATA.OFFI_ADR_NM = CIRADR.ADR_NM;
            CICFB12.DATA.OFFI_POST_CD = CIRADR.POST_CD;
        } else if (CIRADR.KEY.ADR_TYPE.equalsIgnoreCase("270")) {
            CICFB12.DATA.STMT_CNTY_CD = CIRADR.CNTY_CD;
            CICFB12.DATA.STMT_ADR_NM = CIRADR.ADR_NM;
            CICFB12.DATA.STMT_POST_CD = CIRADR.POST_CD;
        } else {
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIB12";
        SCCFMT.DATA_PTR = CICFB12;
        SCCFMT.DATA_LEN = 4394;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
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
