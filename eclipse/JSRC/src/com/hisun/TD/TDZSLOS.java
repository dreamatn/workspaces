package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
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

public class TDZSLOS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    DBParm TDTSTS_RD;
    brParm TDTBVT_BR = new brParm();
    DBParm DCTIAACR_RD;
    String K_AP_MMO = "TD";
    String K_PRDP_TYP = "PRDP";
    String K_SLOS_APP_FMT = "TD501";
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_STS_TBL = "0004";
    String PGM_SCZGSEQ = "SCZGSEQ";
    String WS_BVT_AC = " ";
    String WS_ERR_MSG = " ";
    short WS_RC = 0;
    int WS_END_DATE = 0;
    String WS_BVT_STSW = " ";
    String WS_STS_BUSI_NO = " ";
    long WS_BV_DB = 0;
    long WS_BV_TL = 0;
    String WS_ACO_AC = " ";
    String WS_PROD_CD = " ";
    TDZSLOS_WS_CHECK_INFO WS_CHECK_INFO = new TDZSLOS_WS_CHECK_INFO();
    String WS_LOS_NO = " ";
    char WS_PLOSS_FIG = ' ';
    char WS_TABLE_FLG = ' ';
    char WS_BPTLOSS_FLG = ' ';
    int WS_BRANCH_BR1 = 0;
    int WS_BRANCH_BR2 = 0;
    char WS_STS_FLG = ' ';
    char WS_BV_FLG = ' ';
    char WS_YBT_AC_FLAG = ' ';
    char WS_TDTBVT_REC = ' ';
    char WS_STARTBR_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    CICACCU CICACCU = new CICACCU();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    SCCSEQ SCCSEQ = new SCCSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    TDCPRDP TDCPRDP = new TDCPRDP();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDCOAPP TDCOAPP = new TDCOAPP();
    DPCPARMP DPCPARMP = new DPCPARMP();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    BPCTCALF BPCTCALF = new BPCTCALF();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCUPARM TDCUPARM = new TDCUPARM();
    DCCIMSTU DCCIMSTU = new DCCIMSTU();
    DCRIAACR DCRIAACR = new DCRIAACR();
    CICQACAC CICQACAC = new CICQACAC();
    TDRCMST TDRCMST = new TDRCMST();
    BPRLOSS BPRLOSS = new BPRLOSS();
    BPCSLOSS BPCSLOSS = new BPCSLOSS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPRGST BPCPRGST = new BPCPRGST();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCPSWDR TDCPSWDR = new TDCPSWDR();
    CICSAGEN CICSAGEN = new CICSAGEN();
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    TDCSLOS TDCSLOS;
    public void MP(SCCGWA SCCGWA, TDCSLOS TDCSLOS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSLOS = TDCSLOS;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, TDCSLOS.BV_CD);
        CEP.TRC(SCCGWA, TDCSLOS.BV_TYPE);
        CEP.TRC(SCCGWA, TDCSLOS.BV_NO);
        CEP.TRC(SCCGWA, TDCSLOS.AC_NO);
        CEP.TRC(SCCGWA, TDCSLOS.AC_SEQ);
        CEP.TRC(SCCGWA, TDCSLOS.AC_NAME);
        CEP.TRC(SCCGWA, TDCSLOS.REG_MTH);
        CEP.TRC(SCCGWA, TDCSLOS.REG_CHNL);
        CEP.TRC(SCCGWA, TDCSLOS.HLD_FLG);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSLOS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSLOS.PSWN);
        if (TDCSLOS.PSWN.trim().length() > 0) {
            B001_CHANG_PSWD();
        }
        B010_GET_INF_PROC();
        R000_READ_BV_NO_FROM_DB();
        CEP.TRC(SCCGWA, "F-BUG5");
        CEP.TRC(SCCGWA, TDCSLOS.REG_CHNL);
        WS_STARTBR_FLG = 'N';
        B100_CHK_INPUT_PROC();
        if (TDCSLOS.REG_MTH == '1') {
        }
        R000_GET_END_DATE();
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0") 
            && (TDCSLOS.REG_MTH == '1' 
            || TDCSLOS.REG_MTH == '0')) {
            CEP.TRC(SCCGWA, "-----------1111111111111");
            WS_BPTLOSS_FLG = 'W';
            R000_GET_LOST_NO();
            B280_30_WRITE_BPTLOSS_PROC();
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDCSLOS.REG_MTH == '1' 
            && TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "-----------222222222222");
            WS_BPTLOSS_FLG = 'U';
            B280_20_UPDATE_BPTLOSS_PROC();
            WS_BPTLOSS_FLG = 'W';
            R000_GET_LOST_NO();
            B280_30_WRITE_BPTLOSS_PROC();
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDCSLOS.REG_MTH == '2' 
            || (TDCSLOS.REG_MTH == '3' 
            && TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1"))) {
            CEP.TRC(SCCGWA, "-----------333333333333");
            WS_BPTLOSS_FLG = 'U';
            B280_20_UPDATE_BPTLOSS_PROC();
            WS_BPTLOSS_FLG = 'W';
            R000_GET_LOST_NO();
            B280_30_WRITE_BPTLOSS_PROC();
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B180_AGENT_INF_PORC();
        }
        B250_UPDATE_TDTSTS();
        B260_UPD_TDTBVT();
        B230_WRI_NFIN_HIS_PROC();
        if (TDCSLOS.REG_MTH != '0' 
            && TDCSLOS.REG_MTH != '3') {
            B510_FEE_PROC();
        }
        B300_FMT_OUTPUT_PROC();
    }
    public void B010_GET_INF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        CEP.TRC(SCCGWA, TDCSLOS.AC_NO);
        TDRCMST.KEY.AC_NO = TDCSLOS.AC_NO;
        T000_READ_TDTCMST();
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRCMST.STS);
        CEP.TRC(SCCGWA, TDRCMST.BV_TYP);
        CEP.TRC(SCCGWA, TDRCMST.CI_TYP);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_INF);
        WS_BVT_AC = TDCSLOS.AC_NO;
        CEP.TRC(SCCGWA, TDRCMST.STS);
        if (TDRCMST.STS != '0') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
        }
        if (TDRCMST.BV_TYP == '0' 
            && TDCSLOS.AC_SEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CEP.TRC(SCCGWA, TDCSLOS.AC_NO);
            CEP.TRC(SCCGWA, TDCSLOS.AC_SEQ);
            CICQACAC.DATA.AGR_NO = TDCSLOS.AC_NO;
            CICQACAC.DATA.AGR_SEQ = TDCSLOS.AC_SEQ;
            CICQACAC.DATA.BV_NO = TDCSLOS.BV_NO;
            S000_CALL_CIZQACAC();
            WS_ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, WS_ACO_AC);
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = WS_ACO_AC;
            CEP.TRC(SCCGWA, TDRSMST.KEY.ACO_AC);
            T000_READ_TDTSMST();
            if (TDRSMST.STSW == null) TDRSMST.STSW = "";
            JIBS_tmp_int = TDRSMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
            if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PLED_N_EXC);
            }
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
            CEP.TRC(SCCGWA, TDRSMST.BV_TYP);
            WS_BVT_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            CEP.TRC(SCCGWA, TDRSMST.ACO_STS);
            if (TDRSMST.ACO_STS != '0') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED);
            }
        } else {
            if (TDRCMST.BV_TYP == '2' 
                || TDRCMST.BV_TYP == '3') {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
                CEP.TRC(SCCGWA, TDRSMST.AC_NO);
                CEP.TRC(SCCGWA, TDRSMST.STSW);
                T000_READ_TDTSMST_A();
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                    CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PLED_N_EXC);
                }
            }
        }
        if (TDCSLOS.DRAW_MTH != TDRCMST.DRAW_MTH) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_DRAW_MTH_NOT_MATCH);
        }
    }
    public void B001_CHANG_PSWD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPSWDR);
        TDCPSWDR.BV_CD = TDCSLOS.BV_CD;
        TDCPSWDR.BV_TYP = TDCSLOS.BV_TYPE;
        TDCPSWDR.BV_NO = TDCSLOS.BV_NO;
        TDCPSWDR.AC_NO = TDCSLOS.AC_NO;
        TDCPSWDR.AC_NM = TDCSLOS.AC_NAME;
        TDCPSWDR.DRAW_MTH = TDCSLOS.DRAW_MTH;
        TDCPSWDR.ID_TYP = TDCSLOS.ID_TYPE;
        TDCPSWDR.ID_NO = TDCSLOS.ID_NO;
        TDCPSWDR.PSW_N = TDCSLOS.PSWN;
        TDCPSWDR.AC_SEQ = TDCSLOS.AC_SEQ;
        S000_CALL_TDZPSWDR();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDCSLOS.REG_MTH);
        CEP.TRC(SCCGWA, TDCSLOS.ID_TYPE);
        CEP.TRC(SCCGWA, "F-BUG4");
        CEP.TRC(SCCGWA, TDCSLOS.BV_TYPE);
        CEP.TRC(SCCGWA, TDCSLOS.REG_CHNL);
        B100_01_CHK_CI_PROC();
        WS_BVT_STSW = TDRBVT.STSW;
    }
    public void B100_01_CHK_CI_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = TDCSLOS.AC_NO;
        CEP.TRC(SCCGWA, CICACCU.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICACCU.DATA.ENTY_TYP);
        S000_CALL_CIZACCU();
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_TYPE);
        if (!TDCSLOS.ID_TYPE.equalsIgnoreCase(CICACCU.DATA.ID_TYPE)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ID_TYP_ERR);
        }
        CEP.TRC(SCCGWA, CICACCU.DATA.ID_NO);
        CEP.TRC(SCCGWA, TDCSLOS.ID_NO);
        if (!TDCSLOS.ID_NO.equalsIgnoreCase(CICACCU.DATA.ID_NO)) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_ID_NO_ERR);
        }
    }
    public void B100_03_CHK_AC_PROC() throws IOException,SQLException,Exception {
        R000_CHECK_STS_INFO();
    }
    public void B200_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "CNY";
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC);
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void B210_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.TYPE = "NORM ";
        BPCTCALF.INPUT_AREA.BVF_CODE = TDRBVT.BV_CD;
        CEP.TRC(SCCGWA, BPCTCALF.INPUT_AREA.TX_AC);
        BPCTCALF.INPUT_AREA.TX_CCY = "CNY";
        BPCTCALF.INPUT_AREA.PROD_CODE = "BEST0001";
        BPCTCALF.INPUT_AREA.CPN_ID = "BP-U-TLR-BV-USE     ";
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        S000_CALL_BPZFCALF();
    }
    public void B510_FEE_PROC() throws IOException,SQLException,Exception {
        R000_SET_FEE_INFO();
        R000_CALL_FEE();
    }
    public void R000_GET_BR_CITY_FLG() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        IBS.init(SCCGWA, BPCPRGST);
        BPCPRGST.BR1 = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPRGST.BR2 = TDRSMST.CHE_BR;
        S000_CALL_BPZPRGST1();
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
        if (BPCPRGST.BRANCH_FLG == 'N') {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            S000_CALL_BPZPQORG();
            WS_BRANCH_BR1 = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, WS_BRANCH_BR1);
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPQORG.BR = TDRSMST.CHE_BR;
            S000_CALL_BPZPQORG();
            WS_BRANCH_BR2 = BPCPQORG.BRANCH_BR;
            CEP.TRC(SCCGWA, WS_BRANCH_BR1);
            CEP.TRC(SCCGWA, WS_BRANCH_BR2);
            if (WS_BRANCH_BR1 == WS_BRANCH_BR2) {
                BPCPRGST.BRANCH_FLG = 'Y';
            } else {
                if ((WS_BRANCH_BR1 == 104000 
                    || WS_BRANCH_BR1 == 121000) 
                    && (WS_BRANCH_BR2 == 104000 
                    || WS_BRANCH_BR2 == 121000)) {
                    BPCPRGST.BRANCH_FLG = 'Y';
                }
            }
        }
        CEP.TRC(SCCGWA, BPCPRGST.BRANCH_FLG);
    }
    public void R000_SET_FEE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFFTXI);
        BPCFFTXI.TX_DATA.AUH_FLG = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '1';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = "156";
        BPCFFTXI.TX_DATA.CCY_TYPE = '1';
        BPCFFTXI.TX_DATA.SVR_CD = SCCGWA.COMM_AREA.SERV_CODE;
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        CEP.TRC(SCCGWA, BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY);
        S000_CALL_BPZFFTXI();
    }
    public void R000_CALL_FEE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCALF);
        BPCTCALF.INPUT_AREA.FUNC_CODE = 'C';
        BPCTCALF.INPUT_AREA.ATTR_VAL.BNK_FLG = '0';
        BPCTCALF.INPUT_AREA.ATTR_VAL.DC_FLG = '0';
        BPCTCALF.INPUT_AREA.BVF_CODE = TDRBVT.BV_CD;
        BPCTCALF.INPUT_AREA.TX_CCY = "156";
        BPCTCALF.INPUT_AREA.TX_CNT = 1;
        BPCTCALF.INPUT_AREA.TX_CI = CICACCU.DATA.CI_NO;
        BPCTCALF.INPUT_AREA.PROD_CODE1 = WS_PROD_CD;
        CEP.TRC(SCCGWA, BPCTCALF.RC);
        S000_CALL_BPZFCALF();
        CEP.TRC(SCCGWA, BPCTCALF.RC);
    }
    public void S000_CALL_BPZPRGST1() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-STATION", BPCPRGST);
        if (BPCPRGST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRGST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZPSWDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PSWD-RES", TDCPSWDR);
    }
    public void R000_GET_LOST_NO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "LOSEQ";
        BPCSGSEQ.CODE = "01";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_LOS_NO == null) WS_LOS_NO = "";
        JIBS_tmp_int = WS_LOS_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_LOS_NO += " ";
        WS_LOS_NO = JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1) + WS_LOS_NO.substring(2);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_LOS_NO == null) WS_LOS_NO = "";
        JIBS_tmp_int = WS_LOS_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_LOS_NO += " ";
        WS_LOS_NO = WS_LOS_NO.substring(0, 3 - 1) + JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1) + WS_LOS_NO.substring(3 + 2 - 1);
        if (TDRCMST.CI_TYP == '1') {
            if (WS_LOS_NO == null) WS_LOS_NO = "";
            JIBS_tmp_int = WS_LOS_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_LOS_NO += " ";
            WS_LOS_NO = WS_LOS_NO.substring(0, 5 - 1) + "01" + WS_LOS_NO.substring(5 + 2 - 1);
        } else {
            if (WS_LOS_NO == null) WS_LOS_NO = "";
            JIBS_tmp_int = WS_LOS_NO.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) WS_LOS_NO += " ";
            WS_LOS_NO = WS_LOS_NO.substring(0, 5 - 1) + "02" + WS_LOS_NO.substring(5 + 2 - 1);
        }
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_LOS_NO == null) WS_LOS_NO = "";
        JIBS_tmp_int = WS_LOS_NO.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) WS_LOS_NO += " ";
        WS_LOS_NO = WS_LOS_NO.substring(0, 7 - 1) + JIBS_tmp_str[0].substring(9 - 1, 9 + 7 - 1) + WS_LOS_NO.substring(7 + 7 - 1);
    }
    public void B220_WRI_RGTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSEQ);
        SCCSEQ.NAME = "LOST_SEQ";
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        if (TDCSLOS.REG_MTH == '0' 
            || TDCSLOS.REG_MTH == '3') {
            BPCPNHIS.INFO.TX_TYP_CD = "P134";
        } else {
            BPCPNHIS.INFO.TX_TYP_CD = "P135";
        }
        if (WS_ACO_AC.trim().length() == 0) {
            BPCPNHIS.INFO.AC = TDCSLOS.AC_NO;
            BPCPNHIS.INFO.TX_TOOL = TDCSLOS.AC_NO;
        } else {
            BPCPNHIS.INFO.TX_TOOL = TDCSLOS.AC_NO;
            BPCPNHIS.INFO.AC = TDCSLOS.AC_NO;
            BPCPNHIS.INFO.AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        }
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B250_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        if (TDCSLOS.REG_MTH == '2' 
            && TDRSTS.REQ_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH 
            && TDRSTS.REQ_BR != BPCPORUP.DATA_INFO.BBR) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_NOT_ALLOW_INIST);
        }
        B250_01_UNWRITTEN_STS();
        if (TDCSLOS.REG_MTH == '1' 
            || TDCSLOS.REG_MTH == '2') {
            B250_03_WRITTEN_STS();
        }
    }
    public void B250_01_UNWRITTEN_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        if (TDRCMST.BV_TYP == ' ' 
            || TDRCMST.BV_TYP == '0') {
            CEP.TRC(SCCGWA, "----------B250-01------1");
            TDRSTS.KEY.AC_NO = TDCSLOS.AC_NO;
            TDRSTS.KEY.AC_SEQ = TDCSLOS.AC_SEQ;
        } else {
            CEP.TRC(SCCGWA, "----------B250-01------2");
            TDRSTS.KEY.AC_NO = TDCSLOS.AC_NO;
        }
        TDRSTS.KEY.TYPE = '2';
        TDRSTS.KEY.POS = 2;
        TDRSTS.STR_DATE = BPRLOSS.LOS_DT;
        CEP.TRC(SCCGWA, "----------B250-01------3");
        T000_READ_UPDATE_TDTSTS();
        if (TDCSLOS.REG_MTH == '0' 
            || TDCSLOS.REG_MTH == '3') {
            CEP.TRC(SCCGWA, "----------B250-01------4");
            TDRSTS.STS = '1';
            R000_GET_END_DATE();
            TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSTS.END_DATE = WS_END_DATE;
            TDRSTS.REQ_BR = BPCPORUP.DATA_INFO.BBR;
            TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            TDRSTS.REMARK = WS_LOS_NO;
            CEP.TRC(SCCGWA, TDRSTS.END_DATE);
            if (WS_STS_FLG == 'Y') {
                CEP.TRC(SCCGWA, "----------B250-01------5");
                T000_UPDATE_TDTSTS();
            } else {
                CEP.TRC(SCCGWA, "----------B250-01------6");
                T000_WRITE_TDTSTS();
            }
        } else {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDCSLOS.REG_MTH == '1' 
                && TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                CEP.TRC(SCCGWA, "----------B250-01------7");
                if (WS_STS_FLG == 'Y') {
                    CEP.TRC(SCCGWA, "----------B250-01------8");
                    TDRSTS.STS = '0';
                    TDRSTS.END_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                    TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    T000_UPDATE_TDTSTS();
                }
            }
        }
    }
    public void B250_03_WRITTEN_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        if (TDRCMST.BV_TYP == ' ' 
            || TDRCMST.BV_TYP == '0') {
            TDRSTS.KEY.AC_NO = TDCSLOS.AC_NO;
            TDRSTS.KEY.AC_SEQ = TDCSLOS.AC_SEQ;
        } else {
            TDRSTS.KEY.AC_NO = TDCSLOS.AC_NO;
        }
        TDRSTS.KEY.TYPE = '2';
        TDRSTS.KEY.POS = 3;
        TDRSTS.STR_DATE = BPRLOSS.LOS_DT;
        T000_READ_UPDATE_TDTSTS();
        TDRSTS.STS = '1';
        TDRSTS.STR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.END_DATE = 0;
        TDRSTS.BUSI_NO = WS_STS_BUSI_NO;
        TDRSTS.REQ_BR = BPCPORUP.DATA_INFO.BBR;
        TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRSTS.REMARK = WS_LOS_NO;
        if (WS_STS_FLG == 'N') {
            T000_WRITE_TDTSTS();
        } else {
            T000_UPDATE_TDTSTS();
        }
    }
    public void B260_UPD_TDTBVT() throws IOException,SQLException,Exception {
        if (TDCSLOS.REG_MTH == '1' 
            || TDCSLOS.REG_MTH == '2') {
            if (WS_BVT_STSW == null) WS_BVT_STSW = "";
            JIBS_tmp_int = WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_BVT_STSW += " ";
            WS_BVT_STSW = WS_BVT_STSW.substring(0, 3 - 1) + "1" + WS_BVT_STSW.substring(3 + 1 - 1);
            if (WS_BVT_STSW == null) WS_BVT_STSW = "";
            JIBS_tmp_int = WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_BVT_STSW += " ";
            WS_BVT_STSW = WS_BVT_STSW.substring(0, 2 - 1) + "0" + WS_BVT_STSW.substring(2 + 1 - 1);
            if (TDCSLOS.HLD_FLG == '0') {
                if (WS_BVT_STSW == null) WS_BVT_STSW = "";
                JIBS_tmp_int = WS_BVT_STSW.length();
                for (int i=0;i<32-JIBS_tmp_int;i++) WS_BVT_STSW += " ";
                WS_BVT_STSW = WS_BVT_STSW.substring(0, 7 - 1) + "1" + WS_BVT_STSW.substring(7 + 1 - 1);
            }
        }
        if (TDCSLOS.REG_MTH == '0') {
            if (WS_BVT_STSW == null) WS_BVT_STSW = "";
            JIBS_tmp_int = WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_BVT_STSW += " ";
            WS_BVT_STSW = WS_BVT_STSW.substring(0, 2 - 1) + "1" + WS_BVT_STSW.substring(2 + 1 - 1);
            if (WS_BVT_STSW == null) WS_BVT_STSW = "";
            JIBS_tmp_int = WS_BVT_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) WS_BVT_STSW += " ";
            WS_BVT_STSW = WS_BVT_STSW.substring(0, 7 - 1) + "1" + WS_BVT_STSW.substring(7 + 1 - 1);
        }
        TDRBVT.STSW = WS_BVT_STSW;
        TDRBVT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        T000_UPDATE_TDTBVT();
    }
    public void B270_UPD_STSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTU);
        DCCIMSTU.VIA_AC = TDCSLOS.AC_NO;
        if (TDCSLOS.HLD_FLG == '1') {
            if (DCCIMSTU.STS_WORD == null) DCCIMSTU.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTU.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTU.STS_WORD += " ";
            DCCIMSTU.STS_WORD = DCCIMSTU.STS_WORD.substring(0, 7 - 1) + "0" + DCCIMSTU.STS_WORD.substring(7 + 1 - 1);
        } else {
            if (DCCIMSTU.STS_WORD == null) DCCIMSTU.STS_WORD = "";
            JIBS_tmp_int = DCCIMSTU.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTU.STS_WORD += " ";
            DCCIMSTU.STS_WORD = DCCIMSTU.STS_WORD.substring(0, 7 - 1) + "1" + DCCIMSTU.STS_WORD.substring(7 + 1 - 1);
        }
        S000_CALL_DCZIMSTU();
    }
    public void B280_20_UPDATE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCSLOS.AC_NO;
        if (TDRSMST.BV_TYP != ' ') {
            TDRSTS.KEY.AC_SEQ = TDCSLOS.AC_SEQ;
        }
        TDRSTS.KEY.TYPE = '2';
        CEP.TRC(SCCGWA, "111111");
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
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            TDRSTS.KEY.POS = 2;
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            TDRSTS.KEY.POS = 3;
        }
        TDRSTS.STS = '1';
        CEP.TRC(SCCGWA, "--------READ----TDTSTS------");
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_SEQ);
        CEP.TRC(SCCGWA, TDRSTS.KEY.TYPE);
        CEP.TRC(SCCGWA, TDRSTS.KEY.POS);
        CEP.TRC(SCCGWA, TDRSTS.STS);
        T000_READ_TDTSTS();
        IBS.init(SCCGWA, BPCSLOSS);
        CEP.TRC(SCCGWA, BPRLOSS.KEY.LOS_NO);
        CEP.TRC(SCCGWA, BPRLOSS.STS);
        BPCSLOSS.FUNC = 'U';
        BPCSLOSS.LOS_NO = TDRSTS.REMARK;
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NO);
        if (TDCSLOS.REG_MTH == '0' 
            || TDCSLOS.REG_MTH == '3') {
            BPCSLOSS.STS = '4';
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDCSLOS.REG_MTH == '1' 
            && TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            BPCSLOSS.STS = '4';
        }
        if (TDCSLOS.REG_MTH == '2') {
            BPCSLOSS.STS = '3';
        }
        BPCSLOSS.OTH_NM = TDCSLOS.AG_NAME;
        BPCSLOSS.OTH_ID_TYP = TDCSLOS.AG_ID_TY;
        BPCSLOSS.OTH_ID_NO = TDCSLOS.AG_ID_NO;
        BPCSLOSS.LOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.HLD_FLG = TDCSLOS.HLD_FLG;
        BPCSLOSS.RLOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.RLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.RLOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.RLOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        S000_CALL_BPZSLOSS();
    }
    public void B280_30_WRITE_BPTLOSS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRLOSS);
        IBS.init(SCCGWA, BPCSLOSS);
        BPCSLOSS.FUNC = 'C';
        BPCSLOSS.LOS_NO = WS_LOS_NO;
        BPCSLOSS.AC = TDCSLOS.AC_NO;
        if (CICACCU.DATA.CI_TYP == '1') {
            BPCSLOSS.PER_FLG = '1';
        } else {
            BPCSLOSS.PER_FLG = '2';
        }
        B231_GET_PRD_INF();
        if (TDCSLOS.REG_MTH == '0' 
            || TDCSLOS.REG_MTH == '3') {
            BPCSLOSS.STS = '2';
            BPCSLOSS.RLOS_DUE_TIME = WS_END_DATE;
        }
        if (TDCSLOS.REG_MTH == '1' 
            || TDCSLOS.REG_MTH == '2') {
            BPCSLOSS.STS = '1';
        }
        CEP.TRC(SCCGWA, TDCSLOS.BV_TYPE);
        if (TDCSLOS.BV_TYPE == '1') {
            BPCSLOSS.BV_TYP = '6';
        } else if (TDCSLOS.BV_TYPE == '2') {
            BPCSLOSS.BV_TYP = '4';
        } else if (TDCSLOS.BV_TYPE == '3') {
            BPCSLOSS.BV_TYP = '5';
        } else if (TDCSLOS.BV_TYPE == '7') {
            BPCSLOSS.BV_TYP = '7';
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_ERR);
        }
        CEP.TRC(SCCGWA, TDRSMST.BAL);
        BPCSLOSS.BV_CODE = TDCSLOS.BV_CD;
        BPCSLOSS.BV_NO = TDCSLOS.BV_NO;
        BPCSLOSS.ID_TYP = TDCSLOS.ID_TYPE;
        BPCSLOSS.ID_NO = TDCSLOS.ID_NO;
        BPCSLOSS.CI_CNM = CICACCU.DATA.CI_CNM;
        BPCSLOSS.OPEN_BR = TDRCMST.OWNER_BR;
        BPCSLOSS.OPEN_AMT = TDRSMST.BAL;
        BPCSLOSS.OTH_NM = TDCSLOS.AG_NAME;
        BPCSLOSS.OTH_ID_TYP = TDCSLOS.AG_ID_TY;
        BPCSLOSS.OTH_ID_NO = TDCSLOS.AG_ID_NO;
        BPCSLOSS.LOS_ORG = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCSLOSS.LOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSLOSS.LOS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCSLOSS.HLD_FLG = TDCSLOS.HLD_FLG;
        BPCSLOSS.LOS_TIME = SCCGWA.COMM_AREA.TR_TIME;
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            BPCSLOSS.OTH_NM = CICGAGA_AGENT_AREA.CI_NM;
            BPCSLOSS.OTH_ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
            BPCSLOSS.OTH_ID_NO = CICGAGA_AGENT_AREA.ID_NO;
            BPCSLOSS.OTH_TELE = CICGAGA_AGENT_AREA.PHONE;
        }
        BPCSLOSS.LOS_RMK = TDCSLOS.LOS_RSN;
        CEP.TRC(SCCGWA, BPCSLOSS.LOS_NM);
        CEP.TRC(SCCGWA, BPCSLOSS.PER_FLG);
        S000_CALL_BPZSLOSS();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOAPP);
        TDCOAPP.LOS_NO = WS_LOS_NO;
        TDCOAPP.LOS_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDCOAPP.REG_METHOD = TDCSLOS.REG_MTH;
        TDCOAPP.BV_TYPE = TDCSLOS.BV_TYPE;
        TDCOAPP.BV_NO = TDCSLOS.BV_NO;
        TDCOAPP.AC_NO = TDCSLOS.AC_NO;
        if (TDCSLOS.BV_TYPE == '2') {
            TDCOAPP.AMT = TDRSMST.BAL;
        }
        if (TDCSLOS.BV_TYPE == '3' 
            || TDCSLOS.BV_TYPE == '6' 
            || TDCSLOS.BV_TYPE == '7') {
            TDCOAPP.AMT = TDRSMST.PBAL;
        }
        TDCOAPP.ID_TYPE = TDCSLOS.ID_TYPE;
        TDCOAPP.ID_NO = TDCSLOS.ID_NO;
        TDCOAPP.AG_ID_TYPE = TDCSLOS.AG_ID_TY;
        TDCOAPP.AG_ID_NO = TDCSLOS.AG_ID_NO;
        TDCOAPP.TR_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_SLOS_APP_FMT;
        SCCFMT.DATA_PTR = TDCOAPP;
        SCCFMT.DATA_LEN = 529;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B231_GET_PRD_INF() throws IOException,SQLException,Exception {
        if (TDRCMST.BV_TYP != '0' 
            && TDRCMST.PROD_CD.trim().length() > 0) {
            WS_PROD_CD = TDRCMST.PROD_CD;
        } else {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
            if (TDRCMST.BV_TYP != '3') {
                CICQACAC.DATA.BV_NO = TDCSLOS.BV_NO;
            }
            S000_CALL_CIZQACAC();
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
            WS_PROD_CD = TDRSMST.PROD_CD;
        }
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        CEP.TRC(SCCGWA, BPCPQPRD.AC_TYPE);
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("029")) {
            BPCSLOSS.AC_TYPE = "02";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("025")) {
            BPCSLOSS.AC_TYPE = "05";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("024")) {
            BPCSLOSS.AC_TYPE = "06";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("027")) {
            BPCSLOSS.AC_TYPE = "07";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("026")) {
            BPCSLOSS.AC_TYPE = "09";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("032")) {
            BPCSLOSS.AC_TYPE = "11";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("035")) {
            BPCSLOSS.AC_TYPE = "12";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("036")) {
            BPCSLOSS.AC_TYPE = "13";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("028")) {
            BPCSLOSS.AC_TYPE = "17";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
            BPCSLOSS.AC_TYPE = "18";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045") 
            || BPCPQPRD.AC_TYPE.equalsIgnoreCase("031")) {
            BPCSLOSS.AC_TYPE = "20";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("041")) {
            BPCSLOSS.AC_TYPE = "26";
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("020")) {
            if (CICACCU.DATA.CI_TYP != '1') {
                BPCSLOSS.AC_TYPE = "15";
            } else {
                BPCSLOSS.AC_TYPE = "03";
            }
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("022")) {
            if (CICACCU.DATA.CI_TYP != '1') {
                BPCSLOSS.AC_TYPE = "19";
            } else {
                BPCSLOSS.AC_TYPE = "08";
            }
        }
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021")) {
            if (CICACCU.DATA.CI_TYP != '1') {
                BPCSLOSS.AC_TYPE = "16";
            } else {
                BPCSLOSS.AC_TYPE = "10";
            }
        }
        CEP.TRC(SCCGWA, BPCSLOSS.AC_TYPE);
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
        CICSAGEN.OUT_AC = TDCSLOS.AC_NO;
        CEP.TRC(SCCGWA, CICSAGEN.OUT_AC);
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "3";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
    }
    public void R000_CHK_MMST_INFO() throws IOException,SQLException,Exception {
        WS_CHECK_INFO.WS_CHECK_STS = DCCIMSTR.DATA.AC_STS;
        WS_CHECK_INFO.WS_CHECK_STSW = DCCIMSTR.DATA.STS_WORD;
        WS_CHECK_INFO.WS_CHECK_PROD_CD = DCCIMSTR.DATA.PRD_CODE;
        R000_CHECK_AC_INFO();
    }
    public void R000_CHK_SMST_INFO() throws IOException,SQLException,Exception {
        WS_CHECK_INFO.WS_CHECK_STS = TDRSMST.ACO_STS;
        WS_CHECK_INFO.WS_CHECK_STSW = TDRSMST.STSW;
        WS_CHECK_INFO.WS_CHECK_AC_BK = "" + TDRSMST.AC_BK;
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_AC_BK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_AC_BK = "0" + WS_CHECK_INFO.WS_CHECK_AC_BK;
        WS_CHECK_INFO.WS_CHECK_PROD_CD = TDRSMST.PROD_CD;
        WS_CHECK_INFO.WS_CHECK_PRDAC_CD = TDRSMST.PRDAC_CD;
        R000_CHECK_AC_INFO();
    }
    public void R000_CHK_BVT_INFO() throws IOException,SQLException,Exception {
        WS_BV_FLG = 'Y';
        if ((TDCSLOS.BV_TYPE == '1' 
            && WS_YBT_AC_FLAG == 'N') 
            && !TDRBVT.BV_CD.equalsIgnoreCase("00040")) {
            WS_BV_FLG = 'N';
        }
        if (TDCSLOS.BV_TYPE == '3' 
            && (!TDRBVT.BV_CD.equalsIgnoreCase("00050") 
            && !TDRBVT.BV_CD.equalsIgnoreCase("00059"))) {
            WS_BV_FLG = 'N';
        }
        if (TDCSLOS.BV_TYPE == '6' 
            && !TDRBVT.BV_CD.equalsIgnoreCase("TDBV6")) {
            WS_BV_FLG = 'N';
        }
        if (TDCSLOS.BV_TYPE == '7' 
            && !TDRBVT.BV_CD.equalsIgnoreCase("00052")) {
            WS_BV_FLG = 'N';
        }
        if (!TDCSLOS.BV_CD.equalsIgnoreCase(TDRBVT.BV_CD)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_CD_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (!TDCSLOS.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        WS_BVT_STSW = TDRBVT.STSW;
        WS_CHECK_INFO.WS_CHECK_BV_CD = TDRBVT.BV_CD;
        if (TDCSLOS.REG_MTH == '0') {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
                S000_ERR_MSG_PROC();
            }
        } else {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
                S000_ERR_MSG_PROC();
            }
        }
        if (WS_STARTBR_FLG == 'N') {
            if (TDRBVT.STSW == null) TDRBVT.STSW = "";
            JIBS_tmp_int = TDRBVT.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
            if (TDRBVT.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_HAS_PLEDGE;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void R000_CHECK_AC_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_STS);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_STSW);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_AC_BK);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_PROD_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_PRDAC_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_BV_CD);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_BV_TYP);
        CEP.TRC(SCCGWA, WS_CHECK_INFO.WS_CHECK_DRAW_MTH);
        if (WS_CHECK_INFO.WS_CHECK_STS == 'C') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_STS == 'R') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            S000_ERR_MSG_PROC();
        }
        if (WS_CHECK_INFO.WS_CHECK_STSW == null) WS_CHECK_INFO.WS_CHECK_STSW = "";
        JIBS_tmp_int = WS_CHECK_INFO.WS_CHECK_STSW.length();
        for (int i=0;i<32-JIBS_tmp_int;i++) WS_CHECK_INFO.WS_CHECK_STSW += " ";
        if (WS_CHECK_INFO.WS_CHECK_STSW.substring(10 - 1, 10 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_IMPAWNED;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_CHECK_STS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFCSTS);
        BPCFCSTS.AP_MMO = K_AP_MMO;
        BPCFCSTS.TBL_NO = K_STS_TBL;
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (CICACCU.DATA.CI_STSW == null) CICACCU.DATA.CI_STSW = "";
        JIBS_tmp_int = CICACCU.DATA.CI_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICACCU.DATA.CI_STSW += " ";
        BPCFCSTS.STATUS_WORD = CICACCU.DATA.CI_STSW + BPCFCSTS.STATUS_WORD.substring(80);
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 31 - 1) + TDRSMST.STSW + BPCFCSTS.STATUS_WORD.substring(31 + 99 - 1);
        if (TDRSMST.STSW == null) TDRSMST.STSW = "";
        JIBS_tmp_int = TDRSMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
        if (TDRSMST.STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_PLED_N_EXC);
        }
        if (BPCFCSTS.STATUS_WORD == null) BPCFCSTS.STATUS_WORD = "";
        JIBS_tmp_int = BPCFCSTS.STATUS_WORD.length();
        for (int i=0;i<300-JIBS_tmp_int;i++) BPCFCSTS.STATUS_WORD += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        BPCFCSTS.STATUS_WORD = BPCFCSTS.STATUS_WORD.substring(0, 130 - 1) + TDRBVT.STSW + BPCFCSTS.STATUS_WORD.substring(130 + 99 - 1);
        CEP.TRC(SCCGWA, BPCFCSTS.STATUS_WORD);
        S000_CALL_BPZFCSTS();
    }
    public void R000_GET_END_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, TDCUPARM);
        TDCUPARM.FUNC = 'I';
        S000_CALL_TDZUPARM();
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.CAN_LOST_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_EXP_DAY);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.DAYS = TDCUPARM.PARM_DATA.LOST_EXP_DAY;
        S000_CALL_SCSSCLDT();
        WS_END_DATE = SCCCLDT.DATE2;
        CEP.TRC(SCCGWA, WS_END_DATE);
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_CALL_SCZENPSW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-ENCRYPT-PASSWORD", SCCENPSW);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "1---READ TDTCMST NOTFND");
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        } else {
            CEP.TRC(SCCGWA, "1---READ TDTCMST FOUND");
            WS_TABLE_FLG = 'Y';
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
    public void T000_READ_TDTSMST_A() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
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
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_READ_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.where = "AC_NO = :TDRSTS.KEY.AC_NO "
            + "AND AC_SEQ = :TDRSTS.KEY.AC_SEQ "
            + "AND TYPE = :TDRSTS.KEY.TYPE "
            + "AND POS = :TDRSTS.KEY.POS "
            + "AND STS = :TDRSTS.STS";
        IBS.READ(SCCGWA, TDRSTS, this, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_I_NO_RECORD);
        }
    }
    public void T000_READ_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        TDTSTS_RD.upd = true;
        IBS.READ(SCCGWA, TDRSTS, TDTSTS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_STS_FLG = 'Y';
        } else {
            WS_STS_FLG = 'N';
        }
    }
    public void T000_UPDATE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_WRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDRSTS.REMARK = WS_LOS_NO;
        TDRSTS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        TDRSTS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.WRITE(SCCGWA, TDRSTS, TDTSTS_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_EXIST;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSTS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO      ", BPCFFTXI);
        CEP.TRC(SCCGWA, BPCFFTXI.RC.RC_CODE);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZFCALF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CAL-FEE      ", BPCTCALF);
        if (BPCTCALF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTCALF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            IBS.init(SCCGWA, SCCBINF);
            SCCBINF.ERR_TYPE = 'P';
            SCCBINF.ERR_ACTION = 'E';
            SCCBINF.ERR_NAME = PGM_SCSSCLDT;
            WS_ERR_MSG = K_SYS_ERR;
            SCCBINF.OTHER_INFO = "CALL-SCSSCLDT ERROR!";
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZSLOSS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MAINT-LOSS-INFO", BPCSLOSS);
        if (BPCSLOSS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSLOSS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
    public void S000_CALL_DCZIMSTU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-UPD-IAMST", DCCIMSTU);
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            T000_READ_BVT_LAST();
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            } else {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PSBK_N_MATCH;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void T000_READ_BVT_LAST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.fst = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BVT_LAST1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND SUBSTR ( STSW , 3 , 1 ) < > '1'";
        TDTBVT_RD.fst = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_BV_LOSS;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_BVT1() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void R000_READ_BV_NO_FROM_DB() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BUG88");
        CEP.TRC(SCCGWA, TDCSLOS.AC_NO);
        CEP.TRC(SCCGWA, TDCSLOS.BV_NO);
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = WS_BVT_AC;
        WS_TDTBVT_REC = 'N';
        R000_READ_TDTBVT();
        if (WS_TDTBVT_REC == 'Y') {
            CEP.TRC(SCCGWA, TDRBVT.BV_NO);
            if (!TDRBVT.BV_NO.equalsIgnoreCase(TDCSLOS.BV_NO)) {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH);
            }
            CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        }
        CEP.TRC(SCCGWA, "F-BUG89");
        CEP.TRC(SCCGWA, TDCSLOS.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            if (TDCSLOS.REG_MTH == '0' 
                || TDCSLOS.REG_MTH == '1' 
                || TDCSLOS.REG_MTH == '3') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_HAS_NOR_LOSS);
            }
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0") 
            && TDRBVT.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("0")) {
            if (TDCSLOS.REG_MTH != '0' 
                && TDCSLOS.REG_MTH != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_STS_NORMAL);
            }
        }
        if (TDRBVT.STSW == null) TDRBVT.STSW = "";
        JIBS_tmp_int = TDRBVT.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRBVT.STSW += " ";
        if (TDRBVT.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            if (TDCSLOS.REG_MTH != '3' 
                && TDCSLOS.REG_MTH != '1') {
                CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_KOUTOU_LOSS);
            }
        }
    }
    public void R000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            R000_READ_TDTBVT_ACO_AC();
        }
    }
    public void R000_READ_TDTBVT_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.DATA.AGR_NO = TDRCMST.KEY.AC_NO;
        CICQACAC.DATA.BV_NO = TDCSLOS.BV_NO;
        CICQACAC.FUNC = 'R';
        S000_CALL_CIZQACAC();
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.upd = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTBVT_REC = 'Y';
        }
    }
    public void T000_STARTBR_TDTBVT1() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.where = "AC = :WS_BVT_AC "
            + "AND SUBSTR ( STSW , 3 , 1 ) < > '1'";
        TDTBVT_BR.rp.upd = true;
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
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
