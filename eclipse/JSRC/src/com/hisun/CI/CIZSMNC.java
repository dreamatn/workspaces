package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSMNC {
    int JIBS_tmp_int;
    int NAM_CI_NM_LEN;
    int BAS_CI_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMNC CICSMNC;
    public void MP(SCCGWA SCCGWA, CICSMNC CICSMNC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMNC = CICSMNC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMNC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSMNC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_WRITE_NORMAL_INFO();
        if (pgmRtn) return;
        B030_WRITE_ID_INFO();
        if (pgmRtn) return;
        B120_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMNC.DATA.CI_NO);
        if (CICSMNC.DATA.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT);
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.CI_TYP);
        if (CICSMNC.DATA.CI_TYP != '2' 
            && CICSMNC.DATA.CI_TYP != '3') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.CI_ATTR);
        if (CICSMNC.DATA.CI_ATTR != '6') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSMNC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSMNC.DATA.CI_NM);
        if (CICSMNC.DATA.ID_TYPE.trim().length() == 0 
            || CICSMNC.DATA.ID_NO.trim().length() == 0 
            || CICSMNC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.FIN_TYPE);
        if (CICSMNC.DATA.FIN_TYPE.trim().length() == 0 
            && CICSMNC.DATA.CI_TYP == '3') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "金融机构类型必须输入");
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.SUB_TYP);
        if (CICSMNC.DATA.SUB_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户类型细分必须输入");
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.CAP_AMT);
        CEP.TRC(SCCGWA, CICSMNC.DATA.CAP_CCY);
        if ((CICSMNC.DATA.CAP_AMT != 0 
            && CICSMNC.DATA.CAP_CCY.trim().length() == 0) 
            || (CICSMNC.DATA.CAP_AMT == 0 
            && CICSMNC.DATA.CAP_CCY.trim().length() > 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACTFD_ONE_NOTIN);
        }
        CEP.TRC(SCCGWA, CICSMNC.DATA.INDUS2);
        if (CICSMNC.DATA.INDUS2.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港行业分类必须输入");
        }
        if (CICSMNC.DATA.ID_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPARMC);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPRMR.FUNC = ' ';
            BPCPRMR.TYP = "PARMC";
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            BPCPRMR.CD = "CIIDO" + BPCPRMR.CD.substring(5);
            if (BPCPRMR.CD == null) BPCPRMR.CD = "";
            JIBS_tmp_int = BPCPRMR.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPCPRMR.CD += " ";
            if (CICSMNC.DATA.ID_TYPE == null) CICSMNC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSMNC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSMNC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSMNC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSMNC.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        } else {
            if (CIRBAS.CI_ATTR != '6') {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CUST_NOT_NOS);
            }
        }
        CEP.TRC(SCCGWA, CIRBAS.OPN_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.HQT_BANK);
        if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != CIRBAS.OPN_BR 
            && SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != SCCGWA.COMM_AREA.HQT_BANK) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_CUST_CANT_MOD);
        }
        if (!CICSMNC.DATA.CI_NO.equalsIgnoreCase(CIRBAS.CI_NM)) {
            IBS.init(SCCGWA, CIRNAM);
            CIRNAM.KEY.CI_NO = CICSMNC.DATA.CI_NO;
            CIRNAM.KEY.NM_TYPE = "01";
            T000_READ_CITNAM_UPD();
            if (pgmRtn) return;
            CIRNAM.CI_NM = CICSMNC.DATA.CI_NM;
            NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
            CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITNAM();
            if (pgmRtn) return;
        }
        CIRBAS.CI_NM = CICSMNC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSMNC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSMNC.DATA.ID_NO;
        CIRBAS.OIC_NO = CICSMNC.DATA.OIC_NO;
        CIRBAS.RESP_CD = CICSMNC.DATA.RESP_CD;
        CIRBAS.SUB_DP = CICSMNC.DATA.SUB_DP;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        if (CICSMNC.DATA.CI_TYP == '3') {
            IBS.init(SCCGWA, CIRFDM);
            CIRFDM.KEY.CI_NO = CICSMNC.DATA.CI_NO;
            T000_READ_CITFDM_UPD();
            if (pgmRtn) return;
            CIRFDM.CI_SUB_TYP = CICSMNC.DATA.SUB_TYP;
            CIRFDM.ORG_TYPE = CICSMNC.DATA.ORG_TYPE;
            CIRFDM.COR_NO = CICSMNC.DATA.COR_NO;
            CIRFDM.MAIN_COR_NO = CICSMNC.DATA.M_COR_NO;
            CIRFDM.INDUS1 = CICSMNC.DATA.INDUS1;
            CIRFDM.INDUS2 = CICSMNC.DATA.INDUS2;
            CIRFDM.REG_AMT = CICSMNC.DATA.REG_AMT;
            CIRFDM.REG_CCY = CICSMNC.DATA.REG_CCY;
            CIRFDM.CAP_AMT = CICSMNC.DATA.CAP_AMT;
            CIRFDM.CAP_CCY = CICSMNC.DATA.CAP_CCY;
            CIRFDM.STK_NO = CICSMNC.DATA.STK_NO;
            CIRFDM.OWN_STR = CICSMNC.DATA.OWN_STR;
            CIRFDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRFDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRFDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITFDM();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRCDM);
            CIRCDM.KEY.CI_NO = CICSMNC.DATA.CI_NO;
            T000_READ_CITCDM_UPD();
            if (pgmRtn) return;
            CIRCDM.CI_SUB_TYP = CICSMNC.DATA.SUB_TYP;
            CIRCDM.ORG_TYPE = CICSMNC.DATA.ORG_TYPE;
            CIRCDM.INDUS1 = CICSMNC.DATA.INDUS1;
            CIRCDM.INDUS2 = CICSMNC.DATA.INDUS2;
            CIRCDM.REG_AMT = CICSMNC.DATA.REG_AMT;
            CIRCDM.REG_CCY = CICSMNC.DATA.REG_CCY;
            CIRCDM.CAP_AMT = CICSMNC.DATA.CAP_AMT;
            CIRCDM.CAP_CCY = CICSMNC.DATA.CAP_CCY;
            CIRCDM.STK_NO = CICSMNC.DATA.STK_NO;
            CIRCDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRCDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRCDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITCDM();
            if (pgmRtn) return;
        }
        if (CICSMNC.DATA.CI_ENM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRNAM);
            CIRNAM.KEY.CI_NO = CICSMNC.DATA.CI_NO;
            CIRNAM.KEY.NM_TYPE = "03";
            T000_READ_CITNAM_UPD();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CIRNAM.CI_NM = CICSMNC.DATA.CI_ENM;
                NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                CIRNAM.OPEN = 'N';
                CIRNAM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_WRITE_CITNAM();
                if (pgmRtn) return;
            } else {
                CIRNAM.CI_NM = CICSMNC.DATA.CI_ENM;
                NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
                CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITNAM();
                if (pgmRtn) return;
            }
        }
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B030_WRITE_ID_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = CICSMNC.DATA.CI_NO;
        T000_READ_CITID_OPEN_UPD();
        if (pgmRtn) return;
        CIRID.KEY.ID_TYPE = CICSMNC.DATA.ID_TYPE;
        CIRID.ID_NO = CICSMNC.DATA.ID_NO;
        CIRID.EXP_DT = CICSMNC.DATA.ID_EXPDT;
        CIRID.ID_RGN = CICSMNC.DATA.ID_RGN;
        CIRID.OPEN = 'Y';
        CIRID.REMARK = CICSMNC.DATA.ID_RMK;
        CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITID();
        if (pgmRtn) return;
    }
    public void B100_WRITE_HISTORY() throws IOException,SQLException,Exception {
    }
    public void B120_OUTPUT_CI_NO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICOCINO);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "CIO01";
        SCCFMT.DATA_PTR = CICOCINO;
        SCCFMT.DATA_LEN = 125;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_TO_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOCINO);
        CICOCINO.DATA.CI_NO = CIRBAS.KEY.CI_NO;
        CICOCINO.DATA.CI_TYP = CIRBAS.CI_TYP;
        CICOCINO.DATA.CI_ATTR = CIRBAS.CI_ATTR;
        CICOCINO.DATA.CI_STSW = CIRBAS.STSW;
        CICOCINO.DATA.IDE_STSW = CIRBAS.IDE_STSW;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CFM_ID_TYPE);
        }
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_READ_CITCDM_UPD() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        CITCDM_RD.upd = true;
        IBS.READ(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_REWRITE_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.REWRITE(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_READ_CITFDM_UPD() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        CITFDM_RD.upd = true;
        IBS.READ(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_REWRITE_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.REWRITE(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_READ_CITID_OPEN_UPD() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        CITID_RD.eqWhere = "CI_NO";
        CITID_RD.where = "OPEN = 'Y'";
        CITID_RD.upd = true;
        IBS.READ(SCCGWA, CIRID, this, CITID_RD);
    }
    public void T000_REWRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.REWRITE(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_READ_CITNAM_UPD() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        CITNAM_RD.upd = true;
        IBS.READ(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_WRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.WRITE(SCCGWA, CIRNAM, CITNAM_RD);
    }
    public void T000_REWRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.REWRITE(SCCGWA, CIRNAM, CITNAM_RD);
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
