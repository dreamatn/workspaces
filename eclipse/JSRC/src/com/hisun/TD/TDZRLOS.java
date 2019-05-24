package com.hisun.TD;

import com.hisun.AI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCENPSW;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZRLOS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTSTS_RD;
    DBParm DCTIAACR_RD;
    brParm TDTBVT_BR = new brParm();
    DBParm TDTCMST_RD;
    String K_RLOS_INF_FMT = "TD522";
    String K_AP_MMO = "TD";
    String K_STS_TBL = "0003";
    String K_STS_TBL_P = "0013";
    String WS_BVT_AC = " ";
    String WS_ERR_MSG = " ";
    long WS_BV_DB = 0;
    long WS_BV_TL = 0;
    TDZRLOS_WS_TABLES_INFO WS_TABLES_INFO = new TDZRLOS_WS_TABLES_INFO();
    TDZRLOS_WS_CHECK_INFO WS_CHECK_INFO = new TDZRLOS_WS_CHECK_INFO();
    char WS_BV_FLG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_TDTBVT_REC = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    CICACCU CICACCU = new CICACCU();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    TDCBVCHG TDCBVCHG = new TDCBVCHG();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDCORLOS TDCORLOS = new TDCORLOS();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    DCCIMSTU DCCIMSTU = new DCCIMSTU();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRCMST TDRCMST = new TDRCMST();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    CICQACAC CICQACAC = new CICQACAC();
    TDCPSWDR TDCPSWDR = new TDCPSWDR();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    TDCRLOS TDCRLOS;
    public void MP(SCCGWA SCCGWA, TDCRLOS TDCRLOS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCRLOS = TDCRLOS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZRLOS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (TDCRLOS.BV_CD.equalsIgnoreCase("002")) {
            TDCRLOS.BV_CD = "C00011";
        }
        R000_READ_BV_NO_FROM_DB();
        B100_CHK_INPUT_PROC();
        B200_BV_REL_LOSS_PROC();
        B300_FMT_OUTPUT_PROC();
    }
    public void B001_CHANG_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPSWDR);
        TDCPSWDR.BV_CD = TDCRLOS.BV_CD;
        TDCPSWDR.BV_TYP = TDCRLOS.BV_TYP;
        TDCPSWDR.BV_NO = TDCRLOS.BV_NO;
        TDCPSWDR.AC_NO = TDCRLOS.AC_NO;
        TDCPSWDR.AC_NM = TDCRLOS.AC_NM;
        TDCPSWDR.DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCPSWDR.ID_TYP = TDCRLOS.ID_TYPE;
        TDCPSWDR.ID_NO = TDCRLOS.ID_NO;
        TDCPSWDR.PSW_N = TDCRLOS.PSWN;
        TDCPSWDR.AC_SEQ = TDCRLOS.AC_SEQ;
        S000_CALL_TDZPSWDR();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCRLOS.PROC_TYP != '1' 
            && TDCRLOS.REG_METHOD == '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PROC_MTH_ERR);
        }
        B100_01_GET_CI_PROC();
        B100_03_GET_AC_REC();
        B100_05_CHK_AC_INFO();
        B100_07_CHK_BVT_INFO();
    }
    public void B100_01_GET_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCRLOS.AC_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        S000_CALL_CIZACCU();
    }
    public void B100_03_GET_AC_REC() throws IOException,SQLException,Exception {
        if (WS_YBT_AC_FLAG == 'Y') {
            WS_CHECK_INFO.WS_CHECK_STS = TDRCMST.STS;
            WS_CHECK_INFO.WS_CHECK_STSW = TDRCMST.STSW;
            WS_CHECK_INFO.WS_CHECK_AC_BR = TDRCMST.OWNER_BR;
            WS_CHECK_INFO.WS_CHECK_PROD_CD = TDRCMST.PROD_CD;
        }
        if (WS_YBT_AC_FLAG == 'N') {
            WS_TABLES_INFO.WS_SMST_BAL = TDRSMST.BAL;
            WS_TABLES_INFO.WS_SMST_PBAL = TDRSMST.PBAL;
            if (TDRCMST.BV_TYP == '3') {
                WS_CHECK_INFO.WS_CHECK_STS = TDRCMST.STS;
            } else {
                WS_CHECK_INFO.WS_CHECK_STS = TDRSMST.ACO_STS;
            }
            WS_CHECK_INFO.WS_CHECK_STSW = TDRSMST.STSW;
            WS_CHECK_INFO.WS_CHECK_AC_BR = TDRSMST.OWNER_BR;
            WS_CHECK_INFO.WS_CHECK_PROD_CD = TDRSMST.PROD_CD;
            WS_CHECK_INFO.WS_CHECK_PRDAC_CD = TDRSMST.PRDAC_CD;
        }
        WS_TABLES_INFO.WS_BVT_BV_CD = TDRBVT.BV_CD;
        WS_TABLES_INFO.WS_BVT_STSW = TDRBVT.STSW;
        CEP.TRC(SCCGWA, TDRBVT.STSW);
    }
    public void B100_05_CHK_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_STS);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_STSW);
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_BR);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_PROD_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_BV_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_BV_TYP);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_MTH);
        if (WS_CHECK_INFO.WS_CHECK_STS == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        CEP.TRC(SCCGWA, TDCRLOS.REG_METHOD);
        if (WS_CHECK_INFO.WS_CHECK_STS == '2') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED);
        }
        CEP.TRC(SCCGWA, TDCRLOS.REG_METHOD);
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        CEP.TRC(SCCGWA, TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1));
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        CEP.TRC(SCCGWA, TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1));
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && TDCRLOS.REG_METHOD != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REG_METHOD_ERR);
        }
        CEP.TRC(SCCGWA, TDCRLOS.REG_METHOD);
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1") 
            && TDCRLOS.REG_METHOD != '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REG_METHOD_ERR);
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (!TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
            && !TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_BV_NOT_LOSS);
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_TYP);
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_STSW);
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCFCSTS.TBL_NO = K_STS_TBL_P;
        } else {
            BPCFCSTS.TBL_NO = K_STS_TBL;
        }
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW.substring(0, 80) + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 101 - 1) + TDRCMST.STSW + BPCFCSTS.STATUS_WORD.substring(101 + 32 - 1);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 151 - 1) + TDRBVT.STSW + BPCFCSTS.STATUS_WORD.substring(151 + 32 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void B100_07_CHK_BVT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCRLOS.BV_CD);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDCRLOS.BV_TYP);
        WS_BV_FLG = 'Y';
        if (!TDCRLOS.BV_CD.equalsIgnoreCase(TDRBVT.BV_CD)) {
            WS_BV_FLG = 'N';
        }
        if (WS_BV_FLG == 'N') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_CD_TYP_NOT_MATCH);
        }
        CEP.TRC(SCCGWA, TDCRLOS.BV_CD);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDCRLOS.REG_CHANNEL);
        if (TDCRLOS.REG_CHANNEL != '4') {
            if (!TDCRLOS.BV_CD.equalsIgnoreCase(TDRBVT.BV_CD)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_CD_NOT_MATCH);
            }
            CEP.TRC(SCCGWA, TDCRLOS.BV_NO);
            CEP.TRC(SCCGWA, TDRBVT.BV_NO);
            if (!TDCRLOS.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
            }
        }
    }
    public void B200_BV_REL_LOSS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCRLOS.PROC_TYP);
        CEP.TRC(SCCGWA, TDCRLOS.REG_METHOD);
        B240_WRI_NFIN_HIS_PROC();
        B250_RLS_STATUS_PROC();
        B260_UPD_DETAIL_REC_PROC();
    }
    public void B280_30_WRITE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'U';
        if (TDRSTS.REMARK == null) TDRSTS.REMARK = "";
        JIBS_tmp_int = TDRSTS.REMARK.length();
        for (int i=0;i<120-JIBS_tmp_int;i++) TDRSTS.REMARK += " ";
        if (!TDRSTS.REMARK.substring(0, 20).equalsIgnoreCase(TDCRLOS.LOS_NO) 
            && TDCRLOS.REG_METHOD != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_LOSS_MTH_ERR);
        }
        BPCSLOSS.LOS_NO = TDRSTS.REMARK;
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, TDRSTS.KEY.POS);
        if (TDRSTS.KEY.POS == 2) {
            BPCSLOSS.STS = '4';
        } else {
            if (TDRSTS.KEY.POS == 3) {
                BPCSLOSS.STS = '3';
            }
        }
        CEP.TRC(SCCGWA, BPCSLOSS.STS);
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSLOSS.RLOS_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSLOSS.RLOS_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSLOSS.RLOS_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            BPCSLOSS.RLOS_TELE = CICGAGA_AGENT_AREA.PHONE;
        } else {
            BPCSLOSS.RLOS_NM = TDCRLOS.AC_NM;
            BPCSLOSS.RLOS_ID_TYP = TDCRLOS.ID_TYPE;
            BPCSLOSS.RLOS_ID_NO = TDCRLOS.ID_NO;
            BPCSLOSS.RLOS_TELE = TDCRLOS.PHONE;
        }
        S000_CALL_BPZSLOSS();
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B240_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P113";
        if (WS_YBT_AC_FLAG == 'Y') {
            BPCPNHIS.INFO.AC = TDCRLOS.AC_NO;
        } else {
            BPCPNHIS.INFO.AC = TDCRLOS.AC_NO;
            BPCPNHIS.INFO.TX_TOOL = TDCRLOS.AC_NO;
            BPCPNHIS.INFO.AC_SEQ = TDCRLOS.AC_SEQ;
        }
        BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        BPCPNHIS.INFO.CCY = TDRSMST.CCY;
        BPCPNHIS.INFO.CCY_TYPE = TDRSMST.CCY_TYP;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        S000_CALL_BPZPNHIS();
    }
    public void B250_RLS_STATUS_PROC() throws IOException,SQLException,Exception {
        B252_REL_LOS_STS();
    }
    public void B252_REL_LOS_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCRLOS.AC_NO;
        TDRSTS.KEY.AC_SEQ = TDCRLOS.AC_SEQ;
        TDRSTS.STS = '1';
        TDRSTS.KEY.TYPE = '2';
        if (TDCRLOS.REG_METHOD == '0') {
            TDRSTS.KEY.POS = 2;
        } else {
            TDRSTS.KEY.POS = 3;
        }
        CEP.TRC(SCCGWA, TDCRLOS.BV_TYP);
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRSTS.KEY.POS);
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, TDRSTS.STS);
        CEP.TRC(SCCGWA, TDRSTS.KEY.TYPE);
        CEP.TRC(SCCGWA, TDRSTS.KEY.POS);
        T000_READ_UPDATE_TDTSTS();
        if (TDRSTS.REQ_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && TDRSTS.REQ_BR != BPCPORUP.DATA_INFO.BBR 
            && TDCRLOS.REG_METHOD != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BR_INPUT_ERR);
        }
        B280_30_WRITE_BPTLOSS_PROC();
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B180_AGENT_INF_PORC();
        }
        TDRSTS.STS = '0';
        TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.BUSI_NO = "                                   ";
        T000_UPDATE_TDTSTS();
    }
    public void B254_CHANGE_BANK_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCBVCHG);
        TDCBVCHG.CHG_TYP = '1';
        TDCBVCHG.LOS_NO = TDCRLOS.LOS_NO;
        TDCBVCHG.BV_NO = TDCRLOS.BV_NO;
        TDCBVCHG.AC_NO = TDCRLOS.AC_NO;
        TDCBVCHG.BV_TYP = TDCRLOS.BV_TYP;
        S000_CALL_TDZBVCHG();
    }
    public void B180_AGENT_INF_PORC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = CICACCU.DATA.CI_NO;
        CICSAGEN.OUT_AC = TDCRLOS.AC_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void B260_UPD_DETAIL_REC_PROC() throws IOException,SQLException,Exception {
        if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
        JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
        if (WS_TABLES_INFO.WS_BVT_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
            JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
            WS_TABLES_INFO.WS_BVT_STSW = WS_TABLES_INFO.WS_BVT_STSW.substring(0, 2 - 1) + "0" + WS_TABLES_INFO.WS_BVT_STSW.substring(2 + 1 - 1);
            if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
            JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
            if (WS_TABLES_INFO.WS_BVT_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
                JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
                WS_TABLES_INFO.WS_BVT_STSW = WS_TABLES_INFO.WS_BVT_STSW.substring(0, 7 - 1) + "0" + WS_TABLES_INFO.WS_BVT_STSW.substring(7 + 1 - 1);
            }
        }
        if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
        JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
        if (WS_TABLES_INFO.WS_BVT_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
            JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
            WS_TABLES_INFO.WS_BVT_STSW = WS_TABLES_INFO.WS_BVT_STSW.substring(0, 3 - 1) + "0" + WS_TABLES_INFO.WS_BVT_STSW.substring(3 + 1 - 1);
            if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
            JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
            if (WS_TABLES_INFO.WS_BVT_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                if (WS_TABLES_INFO.WS_BVT_STSW == null) WS_TABLES_INFO.WS_BVT_STSW = "";
                JIBS_tmp_int = WS_TABLES_INFO.WS_BVT_STSW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_TABLES_INFO.WS_BVT_STSW += " ";
                WS_TABLES_INFO.WS_BVT_STSW = WS_TABLES_INFO.WS_BVT_STSW.substring(0, 7 - 1) + "0" + WS_TABLES_INFO.WS_BVT_STSW.substring(7 + 1 - 1);
            }
        }
        TDRBVT.STSW = WS_TABLES_INFO.WS_BVT_STSW;
        TDRBVT.ISSU_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.ISSU_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.ISSU_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_TDTBVT();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCRLOS.PROC_TYP == '1') {
            B310_PRT_REL_LOS_INFO();
        }
    }
    public void B310_PRT_REL_LOS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCORLOS);
        TDCORLOS.REL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCORLOS.PROC_TYP = TDCRLOS.PROC_TYP;
        TDCORLOS.BV_TYP = TDCRLOS.BV_TYP;
        TDCORLOS.BV_NO = TDCRLOS.BV_NO;
        TDCORLOS.CI_NAME = TDCRLOS.AC_NM;
        TDCORLOS.AC_NO = TDCRLOS.AC_NO;
        if (TDCRLOS.BV_TYP == '1' 
            && WS_YBT_AC_FLAG == 'N') {
            TDCORLOS.AMT = WS_TABLES_INFO.WS_SMST_BAL;
        }
        if (TDCRLOS.BV_TYP == '3' 
            || TDCRLOS.BV_TYP == '6' 
            || TDCRLOS.BV_TYP == '7') {
            TDCORLOS.AMT = WS_TABLES_INFO.WS_SMST_PBAL;
        }
        TDCORLOS.VCH_NO = SCCGWA.COMM_AREA.VCH_NO;
        TDCORLOS.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDCORLOS.SP_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_RLOS_INF_FMT;
        SCCFMT.DATA_PTR = TDCORLOS;
        SCCFMT.DATA_LEN = 359;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_TDZPSWDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSWD-RES", TDCPSWDR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZBVCHG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BV-CHG", TDCBVCHG);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (DCCIMSTR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void S000_CALL_DCZIMSTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-IAMST", DCCIMSTU);
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_READ_TDTBVT_LAST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
            } else {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH);
            }
        }
    }
    public void T000_READ_TDTBVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
        }
    }
    public void T000_READ_UPDATE_TDTBVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.where = "AC_NO = :TDRSTS.KEY.AC_NO "
            + "AND AC_SEQ = :TDRSTS.KEY.AC_SEQ "
            + "AND TYPE = :TDRSTS.KEY.TYPE "
            + "AND POS = :TDRSTS.KEY.POS "
            + "AND STS = :TDRSTS.STS";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, this, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_STARTBR_DCTIAACR_FIRST() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.where = "VIA_AC = :DCRIAACR.KEY.VIA_AC "
            + "AND VCH_NO = :DCRIAACR.VCH_NO "
            + "AND ACCR_FLG = '1'";
        DCTIAACR_RD.fst = true;
        IBS.READ(SCCGWA, DCRIAACR, this, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZFCSTS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-STS-TBL-AUTH", BPCFCSTS);
        if (BPCFCSTS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFCSTS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void R000_READ_BV_NO_FROM_DB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCRLOS.AC_NO);
        CEP.TRC(SCCGWA, TDCRLOS.BV_NO);
        IBS.init(SCCGWA, TDRBVT);
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCRLOS.AC_NO;
        T000_READ_TDTCMST();
        CEP.TRC(SCCGWA, TDCRLOS.PSWN);
        if (TDCRLOS.PSWN.trim().length() > 0) {
            B001_CHANG_PSWD();
        }
        WS_YBT_AC_FLAG = 'Y';
        if (TDRCMST.BV_TYP != '0') {
            TDRBVT.KEY.AC_NO = TDCRLOS.AC_NO;
        }
        if (TDRCMST.BV_TYP == '0') {
            if (TDCRLOS.AC_SEQ == 0 
                && TDCRLOS.BV_NO.trim().length() == 0) {
                CEP.ERR(SCCGWA, "BV-OR-SEQ-INPUT");
            }
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDCRLOS.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDCRLOS.AC_SEQ;
            CICQACAC.DATA.BV_NO = TDCRLOS.BV_NO;
            S000_CALL_CIZQACAC();
            CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
            if (TDRSMST.BV_TYP == ' ') {
                CEP.ERR(SCCGWA, "BV-TYP-TYP");
            }
            if (TDRCMST.BV_TYP != '3') {
                TDRBVT.KEY.AC_NO = TDRSMST.KEY.ACO_AC;
            }
            WS_YBT_AC_FLAG = 'N';
        }
        T000_READ_TDTBVT_LAST();
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
    }
    public void T000_STARTBR_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.where = "AC_NO = :WS_BVT_AC";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_REC = 'Y';
        }
    }
    public void T000_READNEXT_TDTBVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTBVT_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTBVT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_TDTBVT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTBVT_BR);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1---READ TDTCMST NOTFND");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
