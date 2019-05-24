package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSONC {
    int BAS_CI_NM_LEN;
    DBParm BPTORGM_RD;
    int JIBS_tmp_int;
    int NAM_CI_NM_LEN;
    DBParm CITBAS_RD;
    DBParm CITCDM_RD;
    DBParm CITFDM_RD;
    DBParm CITID_RD;
    DBParm CITNAM_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    short WS_I = 0;
    String WS_CI_NO = " ";
    char WS_BAS_FOUND_FLG = ' ';
    String WS_TRT_VIL = " ";
    String WS_OPN_VIL = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRNAM CIRNAM = new CIRNAM();
    CIRID CIRID = new CIRID();
    CICCINO CICCINO = new CICCINO();
    CICOCINO CICOCINO = new CICOCINO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPARMC BPCPARMC = new BPCPARMC();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRORGM BPRORGM = new BPRORGM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSONC CICSONC;
    public void MP(SCCGWA SCCGWA, CICSONC CICSONC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSONC = CICSONC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSONC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSONC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CI_NO();
        if (pgmRtn) return;
        if (CICSONC.DATA.CI_TYP == '3') {
            B030_WRITE_NORMAL_INFO();
            if (pgmRtn) return;
        } else {
            B031_WRITE_NORMAL_INFO_CDM();
            if (pgmRtn) return;
        }
        B040_WRITE_ID_INFO();
        if (pgmRtn) return;
        B050_WRITE_NAM_INFO();
        if (pgmRtn) return;
        B100_WRITE_HISTORY();
        if (pgmRtn) return;
        B120_OUTPUT_CI_NO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSONC.DATA.CI_TYP);
        if (CICSONC.DATA.CI_TYP != '2' 
            && CICSONC.DATA.CI_TYP != '3') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户类型不符");
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.CI_ATTR);
        if (CICSONC.DATA.CI_ATTR != '6') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, "客户性质不符");
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.ID_TYPE);
        CEP.TRC(SCCGWA, CICSONC.DATA.ID_NO);
        CEP.TRC(SCCGWA, CICSONC.DATA.CI_NM);
        if (CICSONC.DATA.ID_TYPE.trim().length() == 0 
            || CICSONC.DATA.ID_NO.trim().length() == 0 
            || CICSONC.DATA.CI_NM.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "三要素必须输�?");
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.FIN_TYPE);
        if (CICSONC.DATA.FIN_TYPE.trim().length() == 0 
            && CICSONC.DATA.CI_TYP == '3') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "金融机构类型必须输入");
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.SUB_TYP);
        if (CICSONC.DATA.SUB_TYP.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "客户类型细分必须输入");
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.CAP_AMT);
        CEP.TRC(SCCGWA, CICSONC.DATA.CAP_CCY);
        if ((CICSONC.DATA.CAP_AMT != 0 
            && CICSONC.DATA.CAP_CCY.trim().length() == 0) 
            || (CICSONC.DATA.CAP_AMT == 0 
            && CICSONC.DATA.CAP_CCY.trim().length() > 0)) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACTFD_ONE_NOTIN);
        }
        CEP.TRC(SCCGWA, CICSONC.DATA.INDUS2);
        if (CICSONC.DATA.INDUS2.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, "香港行业分类必须输入");
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSONC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSONC.DATA.ID_NO;
        CIRBAS.CI_NM = CICSONC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        T000_READ_CITBAS_BY_IDNM_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FOUND_FLG = 'N';
        } else {
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_TRT_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_TRT_VIL);
            IBS.init(SCCGWA, BPRORGM);
            BPRORGM.KEY.BR = CIRBAS.OPN_BR;
            BPTORGM_RD = new DBParm();
            BPTORGM_RD.TableName = "BPTORGM";
            IBS.READ(SCCGWA, BPRORGM, BPTORGM_RD);
            WS_OPN_VIL = BPRORGM.VIL_TYP;
            CEP.TRC(SCCGWA, WS_OPN_VIL);
            if (!WS_TRT_VIL.equalsIgnoreCase(WS_OPN_VIL)) {
                WS_BAS_FOUND_FLG = 'N';
            } else {
                if (CIRBAS.CI_ATTR == '1' 
                    || CIRBAS.CI_ATTR == '6') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_EXIST);
                }
                WS_BAS_FOUND_FLG = 'Y';
                WS_CI_NO = CIRBAS.KEY.CI_NO;
            }
        }
        if (CICSONC.DATA.ID_TYPE.trim().length() > 0) {
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
            if (CICSONC.DATA.ID_TYPE == null) CICSONC.DATA.ID_TYPE = "";
            JIBS_tmp_int = CICSONC.DATA.ID_TYPE.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) CICSONC.DATA.ID_TYPE += " ";
            BPCPRMR.CD = BPCPRMR.CD.substring(0, 6 - 1) + CICSONC.DATA.ID_TYPE + BPCPRMR.CD.substring(6 + 5 - 1);
            BPCPRMR.DAT_PTR = BPCPARMC;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CI_NO() throws IOException,SQLException,Exception {
        if (WS_BAS_FOUND_FLG == 'N') {
            CEP.TRC(SCCGWA, "CALL CIZCINO");
            IBS.init(SCCGWA, CICCINO);
            S000_CALL_CIZCINO();
            if (pgmRtn) return;
            WS_CI_NO = CICCINO.DATA.CI_NO;
        }
        CEP.TRC(SCCGWA, WS_CI_NO);
    }
    public void B030_WRITE_NORMAL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = CICSONC.DATA.CI_TYP;
        CIRBAS.CI_ATTR = CICSONC.DATA.CI_ATTR;
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        CIRBAS.CI_NM = CICSONC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSONC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSONC.DATA.ID_NO;
        CIRBAS.OIC_NO = CICSONC.DATA.OIC_NO;
        CIRBAS.RESP_CD = CICSONC.DATA.RESP_CD;
        CIRBAS.SUB_DP = CICSONC.DATA.SUB_DP;
        CIRBAS.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CIRBAS.OWNER_BK = BPCPQORG.BRANCH_BR;
        CIRBAS.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (WS_BAS_FOUND_FLG == 'N') {
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, CIRFDM);
        CIRFDM.KEY.CI_NO = WS_CI_NO;
        CIRFDM.CI_SUB_TYP = CICSONC.DATA.SUB_TYP;
        CIRFDM.ORG_TYPE = CICSONC.DATA.ORG_TYPE;
        CIRFDM.FIN_TYPE = CICSONC.DATA.FIN_TYPE;
        CIRFDM.COR_NO = CICSONC.DATA.COR_NO;
        CIRFDM.MAIN_COR_NO = CICSONC.DATA.M_COR_NO;
        CIRFDM.INDUS1 = CICSONC.DATA.INDUS1;
        CIRFDM.INDUS2 = CICSONC.DATA.INDUS2;
        CIRFDM.REG_AMT = CICSONC.DATA.REG_AMT;
        CIRFDM.REG_CCY = CICSONC.DATA.REG_CCY;
        CIRFDM.CAP_AMT = CICSONC.DATA.CAP_AMT;
        CIRFDM.CAP_CCY = CICSONC.DATA.CAP_CCY;
        CIRFDM.STK_NO = CICSONC.DATA.STK_NO;
        CIRFDM.OWN_STR = CICSONC.DATA.OWN_STR;
        CIRFDM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFDM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFDM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITFDM();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B031_WRITE_NORMAL_INFO_CDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = WS_CI_NO;
        CIRBAS.CI_TYP = CICSONC.DATA.CI_TYP;
        CIRBAS.CI_ATTR = CICSONC.DATA.CI_ATTR;
        CIRBAS.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "1" + CIRBAS.STSW.substring(1);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 24 - 1) + "1" + CIRBAS.STSW.substring(24 + 1 - 1);
        CIRBAS.CI_NM = CICSONC.DATA.CI_NM;
        BAS_CI_NM_LEN = CIRBAS.CI_NM.length();
        CIRBAS.ID_TYPE = CICSONC.DATA.ID_TYPE;
        CIRBAS.ID_NO = CICSONC.DATA.ID_NO;
        CIRBAS.OIC_NO = CICSONC.DATA.OIC_NO;
        CIRBAS.RESP_CD = CICSONC.DATA.RESP_CD;
        CIRBAS.SUB_DP = CICSONC.DATA.SUB_DP;
        CIRBAS.OPN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CIRBAS.OWNER_BK = BPCPQORG.BRANCH_BR;
        CIRBAS.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        if (WS_BAS_FOUND_FLG == 'N') {
            T000_WRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, CIRCDM);
        CIRCDM.KEY.CI_NO = WS_CI_NO;
        CIRCDM.CI_SUB_TYP = CICSONC.DATA.SUB_TYP;
        CIRCDM.ORG_TYPE = CICSONC.DATA.ORG_TYPE;
        CIRCDM.INDUS1 = CICSONC.DATA.INDUS1;
        CIRCDM.INDUS2 = CICSONC.DATA.INDUS2;
        CIRCDM.REG_AMT = CICSONC.DATA.REG_AMT;
        CIRCDM.REG_CCY = CICSONC.DATA.REG_CCY;
        CIRCDM.CAP_AMT = CICSONC.DATA.CAP_AMT;
        CIRCDM.CAP_CCY = CICSONC.DATA.CAP_CCY;
        CIRCDM.STK_NO = CICSONC.DATA.STK_NO;
        CIRCDM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRCDM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRCDM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRCDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITCDM();
        if (pgmRtn) return;
        R000_TRANS_DATA_TO_OUTPUT();
        if (pgmRtn) return;
    }
    public void B040_WRITE_ID_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRID);
        CIRID.KEY.CI_NO = WS_CI_NO;
        CIRID.KEY.ID_TYPE = CICSONC.DATA.ID_TYPE;
        CIRID.ID_NO = CICSONC.DATA.ID_NO;
        CIRID.EXP_DT = CICSONC.DATA.ID_EXPDT;
        CIRID.ID_RGN = CICSONC.DATA.ID_RGN;
        CIRID.OPEN = 'Y';
        CIRID.REMARK = CICSONC.DATA.ID_RMK;
        CIRID.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRID.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRID.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRID.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITID();
        if (pgmRtn) return;
    }
    public void B050_WRITE_NAM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRNAM);
        CIRNAM.KEY.CI_NO = WS_CI_NO;
        CIRNAM.KEY.NM_TYPE = "01";
        CIRNAM.CI_NM = CICSONC.DATA.CI_NM;
        NAM_CI_NM_LEN = CIRNAM.CI_NM.length();
        CIRNAM.OPEN = 'Y';
        CIRNAM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRNAM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRNAM.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRNAM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRNAM.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRNAM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITNAM();
        if (pgmRtn) return;
        if (CICSONC.DATA.CI_ENM.trim().length() > 0) {
            IBS.init(SCCGWA, CIRNAM);
            CIRNAM.KEY.CI_NO = WS_CI_NO;
            CIRNAM.KEY.NM_TYPE = "03";
            CIRNAM.CI_NM = CICSONC.DATA.CI_ENM;
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
        }
    }
    public void B100_WRITE_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = WS_CI_NO;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
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
    public void S000_CALL_CIZCINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-OPEN-CI-NO", CICCINO);
        if (CICCINO.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCINO.RC);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
    } //FROM #ENDIF
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPQORG.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_NOT_CFM_ID_TYPE);
        }
    }
    public void T000_READ_CITBAS_BY_IDNM_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.eqWhere = "ID_TYPE,ID_NO,CI_NM";
        CITBAS_RD.where = "SUBSTR ( STSW , 3 , 1 ) = '0' "
            + "AND SUBSTR ( STSW , 23 , 1 ) = '0'";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, this, CITBAS_RD);
    }
    public void T000_WRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.WRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_WRITE_CITCDM() throws IOException,SQLException,Exception {
        CITCDM_RD = new DBParm();
        CITCDM_RD.TableName = "CITCDM";
        IBS.WRITE(SCCGWA, CIRCDM, CITCDM_RD);
    }
    public void T000_WRITE_CITFDM() throws IOException,SQLException,Exception {
        CITFDM_RD = new DBParm();
        CITFDM_RD.TableName = "CITFDM";
        IBS.WRITE(SCCGWA, CIRFDM, CITFDM_RD);
    }
    public void T000_WRITE_CITID() throws IOException,SQLException,Exception {
        CITID_RD = new DBParm();
        CITID_RD.TableName = "CITID";
        IBS.WRITE(SCCGWA, CIRID, CITID_RD);
    }
    public void T000_WRITE_CITNAM() throws IOException,SQLException,Exception {
        CITNAM_RD = new DBParm();
        CITNAM_RD.TableName = "CITNAM";
        IBS.WRITE(SCCGWA, CIRNAM, CITNAM_RD);
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
