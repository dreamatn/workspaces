package com.hisun.TD;

import com.hisun.DD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCBINF;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.DC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DP.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZPSWDR {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    brParm TDTBVT_BR = new brParm();
    brParm TDTSTS_BR = new brParm();
    DBParm TDTSTS_RD;
    DBParm TDTBVT_RD;
    brParm DCTIACCY_BR = new brParm();
    String K_PSWDR_I_FMT = "TD530";
    String K_PTPM_O_FMT = "TD525";
    short K_EFF_DAYS = 7;
    String K_SYS_ERR = "SC6001";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    TDZPSWDR_WS_RC_MSG WS_RC_MSG = new TDZPSWDR_WS_RC_MSG();
    char WS_BVT_DB_FLG = ' ';
    char WS_TSTS_DB_FLG = ' ';
    int WS_STR_DATE = 0;
    String WS_SUB_AC = " ";
    TDZPSWDR_WS_TABLES_INFO WS_TABLES_INFO = new TDZPSWDR_WS_TABLES_INFO();
    TDZPSWDR_WS_CHECK_INFO WS_CHECK_INFO = new TDZPSWDR_WS_CHECK_INFO();
    String WS_PSW = " ";
    int WS_EFF_DT = 0;
    String WS_LOS_NO = " ";
    double WS_TD_TOT_BAL = 0;
    int WS_PSW_REST_DT = 0;
    char WS_YBT_AC_FLAG = ' ';
    char WS_STS_FLG = ' ';
    char WS_LOSS_FLG = ' ';
    char WS_DCTIACCY_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    DCCUIQMC DCCUIQMC = new DCCUIQMC();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICSSCH CICSSCH = new CICSSCH();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCMST TDRCMST = new TDRCMST();
    TDRBVT TDRBVT = new TDRBVT();
    TDRSTS TDRSTS = new TDRSTS();
    TDCOPSRI TDCOPSRI = new TDCOPSRI();
    TDCOPTPO TDCOPTPO = new TDCOPTPO();
    DPCPARMP DPCPARMP = new DPCPARMP();
    CICACCU CICACCU = new CICACCU();
    DCCUQSAC DCCUQSAC = new DCCUQSAC();
    DCRIACCY DCRIACCY = new DCRIACCY();
    BPCEX BPCEX = new BPCEX();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    TDCUPARM TDCUPARM = new TDCUPARM();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDCACM TDCACM = new TDCACM();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    TDCPSWDR TDCPSWDR;
    public void MP(SCCGWA SCCGWA, TDCPSWDR TDCPSWDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCPSWDR = TDCPSWDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZPSWDR return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_LOSS_FLG = 'N';
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CEP.TRC(SCCGWA, TDCPSWDR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCUPARM);
        TDCUPARM.FUNC = 'I';
        S000_CALL_TDZUPARM();
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.RESET_PWD_EFF_DAYS);
        CEP.TRC(SCCGWA, TDCUPARM.PARM_DATA.LOST_NEWUSE_DAYS);
        CEP.TRC(SCCGWA, TDCPSWDR.PSW_N);
        CEP.TRC(SCCGWA, TDCPSWDR.BV_TYP);
        CEP.TRC(SCCGWA, TDCPSWDR.AC_NO);
        CEP.TRC(SCCGWA, TDCPSWDR.DRAW_MTH);
        CEP.TRC(SCCGWA, TDCPSWDR.FLG);
        CEP.TRC(SCCGWA, TDCPSWDR.ID_TYP);
        CEP.TRC(SCCGWA, TDCPSWDR.ID_NO);
        CEP.TRC(SCCGWA, TDCPSWDR.AC_NM);
        B100_CHK_INPUT_PROC();
        B200_GET_CI_INFO();
        if (TDCPSWDR.FLG == 'Y') {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCPSWDR.AC_NO;
            T000_READ_TDTCMST_Y();
            CEP.TRC(SCCGWA, TDRCMST.STS);
            CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
            if (TDRCMST.DRAW_MTH == '1' 
                || TDRCMST.DRAW_MTH == '5') {
                if (TDRCMST.DRAW_MTH != ' ') {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PSW_EXIST_ERR;
                    S000_ERR_MSG_PROC();
                }
            }
        } else {
            IBS.init(SCCGWA, TDRCMST);
            TDRCMST.KEY.AC_NO = TDCPSWDR.AC_NO;
            T000_READ_TDTCMST();
            if (TDRCMST.STSW == null) TDRCMST.STSW = "";
            JIBS_tmp_int = TDRCMST.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
            TDRCMST.STSW = TDRCMST.STSW.substring(0, 4 - 1) + "0" + TDRCMST.STSW.substring(4 + 1 - 1);
            TDRCMST.ERR_NUM = 0;
            TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_REWRITE_TDTCMST();
        }
        B216_CHECK_ID_INFO();
        B214_CHECK_AC_BV_INFO();
        B220_RESET_DRAW_PSWD();
        B250_WRI_NFIN_HIS_PROC();
        B300_FMT_OUTPUT_PROC();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCPSWDR.PSW_N.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            S000_ERR_MSG_PROC();
        }
        if (TDCPSWDR.BV_TYP == '1') {
            B110_GET_BV_TYP_UNT();
        }
    }
    public void B110_GET_BV_TYP_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRBVT);
        TDRBVT.KEY.AC_NO = TDCPSWDR.AC_NO;
        T000_READ_BVT();
    }
    public void B200_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.AGR_NO = TDCPSWDR.AC_NO;
        CICCUST.FUNC = 'A';
        S000_CALL_CIZCUST();
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
    }
    public void B200_CHANGE_DRAW_PSWD_PROC() throws IOException,SQLException,Exception {
        B216_CHECK_ID_INFO();
        B210_GET_AC_BV_INFO();
        if (TDCPSWDR.BV_TYP != '1') {
            B214_CHECK_AC_BV_INFO();
        }
        B220_RESET_DRAW_PSWD();
        B250_WRI_NFIN_HIS_PROC();
    }
    public void B400_SET_KH_PSW() throws IOException,SQLException,Exception {
        B210_GET_AC_BV_INFO();
        B220_RESET_DRAW_PSWD();
        B250_WRI_NFIN_HIS_PROC();
        WS_EFF_DT = TDRSMST.VAL_DATE;
    }
    public void B210_GET_AC_BV_INFO() throws IOException,SQLException,Exception {
        if (TDCPSWDR.BV_TYP == '7') {
            if (TDCPSWDR.AC_SEQ != 0) {
                IBS.init(SCCGWA, DCCUQSAC);
                DCCUQSAC.INP_DATA.AC = TDCPSWDR.AC_NO;
                DCCUQSAC.INP_DATA.SEQ = TDCPSWDR.AC_SEQ;
                S000_CALL_DCZUQSAC();
                WS_SUB_AC = DCCUQSAC.OUTP_DATA.SUB_AC;
            } else {
                if (TDCPSWDR.BV_NO.trim().length() > 0) {
                    IBS.init(SCCGWA, DCCPACTY);
                    DCCPACTY.INPUT.FUNC = '3';
                    DCCPACTY.INPUT.AC = TDCPSWDR.AC_NO;
                    DCCPACTY.INPUT.VCH_NO = TDCPSWDR.BV_NO;
                    S000_CALL_DCZPACTY();
                    WS_SUB_AC = DCCPACTY.OUTPUT.STD_AC;
                } else {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_MUST_IPT_BV_NO;
                    S000_ERR_MSG_PROC();
                }
            }
        }
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCPSWDR.AC_NO;
        T000_READ_TDTCMST();
        WS_TABLES_INFO.WS_BVT_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_CHECK_INFO.WS_CHECK_DRAW_MTH = TDRCMST.DRAW_MTH;
        WS_TABLES_INFO.WS_BVT_DRAW_INF = TDRCMST.DRAW_INF;
        WS_CHECK_INFO.WS_CHECK_DRAW_INF = TDRCMST.DRAW_INF;
        WS_TABLES_INFO.WS_BVT_STSW = TDRCMST.STSW;
        WS_CHECK_INFO.WS_CHECK_BVT_STSW = TDRCMST.STSW;
        if (TDCPSWDR.FLG == 'Y' 
            && TDRCMST.DRAW_MTH == '1' 
            && TDRCMST.DRAW_INF.trim().length() > 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_EXIST;
            S000_ERR_MSG_PROC();
        }
        WS_TABLES_INFO.WS_SMST_STS = TDRCMST.STS;
        WS_CHECK_INFO.WS_CHECK_AC_STS = TDRCMST.STS;
        WS_TABLES_INFO.WS_SMST_STSW = TDRCMST.STSW;
        WS_CHECK_INFO.WS_CHECK_AC_STSW = TDRCMST.STSW;
        WS_TABLES_INFO.WS_SMST_AC_BR = TDRCMST.OWNER_BR;
        WS_CHECK_INFO.WS_CHECK_AC_BR = TDRCMST.OWNER_BR;
    }
    public void B210_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        S000_CALL_DCCIMSTR();
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
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
    public void S000_CALL_DCZUQSAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-SUB-AC", DCCUQSAC);
        if (DCCUQSAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUQSAC.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY);
        CEP.TRC(SCCGWA, DCCPACTY.RC);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void B214_CHECK_AC_BV_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCPSWDR.AC_NO;
        T000_READ_TDTCMST();
        CEP.TRC(SCCGWA, TDRCMST.STS);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
        if (TDRCMST.STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
        }
        if (TDRCMST.STS == '2') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_RESERVED;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        if (TDRCMST.DRAW_MTH != '1' 
            && TDRCMST.DRAW_MTH != '5') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_NON_VIA_PWD_DRAW;
            S000_ERR_MSG_PROC();
        }
    }
    public void B260_RLS_STATUS_PROC() throws IOException,SQLException,Exception {
        B263_GET_RGTLOSS();
        B261_UPD_RGTLOSS_PROC();
        B262_RLS_STS_PROC();
    }
    public void B263_GET_RGTLOSS() throws IOException,SQLException,Exception {
    }
    public void B261_UPD_RGTLOSS_PROC() throws IOException,SQLException,Exception {
    }
    public void B262_RLS_STS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSTS);
        TDRSTS.KEY.AC_NO = TDCPSWDR.AC_NO;
        TDRSTS.KEY.TYPE = '2';
        TDRSTS.KEY.POS = 6;
        TDRSTS.STS = '1';
        CEP.TRC(SCCGWA, TDRSTS.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRSTS.STS);
        CEP.TRC(SCCGWA, TDRSTS.KEY.POS);
        T000_STARTBR_TSTS();
        T000_READNEXT_TSTS();
        CEP.TRC(SCCGWA, WS_TSTS_DB_FLG);
        while (WS_TSTS_DB_FLG != 'N') {
            if (TDRSTS.END_DATE == 0 
                && TDCUPARM.PARM_DATA.RESET_PWD_EFF_DAYS > 0) {
                IBS.init(SCCGWA, SCCCLDT);
                SCCCLDT.DATE1 = TDRSTS.STR_DATE;
                SCCCLDT.DAYS = TDCUPARM.PARM_DATA.RESET_PWD_EFF_DAYS;
                S000_CALL_SCSSCLDT();
                WS_EFF_DT = SCCCLDT.DATE2;
            } else {
                WS_EFF_DT = TDRSTS.END_DATE;
            }
            if (WS_STS_FLG == 'N' 
                || TDRSTS.STS == '0') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_PSW_LOSS;
                S000_ERR_MSG_PROC();
            } else {
                TDRSTS.STS = '0';
                TDRSTS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                TDRSTS.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                TDRSTS.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSTS.BUSI_NO = "                                   ";
                T000_REWRITE_TDTSTS();
            }
            WS_STR_DATE = TDRSTS.STR_DATE;
            CEP.TRC(SCCGWA, "F-BUG1");
            CEP.TRC(SCCGWA, WS_STR_DATE);
            T000_READNEXT_TSTS();
        }
    }
    public void B216_CHECK_ID_INFO() throws IOException,SQLException,Exception {
        if (!TDCPSWDR.ID_TYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE) 
            || !TDCPSWDR.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ID_NOT_MACTH;
            S000_ERR_MSG_PROC();
        }
    }
    public void B220_RESET_DRAW_PSWD() throws IOException,SQLException,Exception {
        if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            IBS.init(SCCGWA, TDCACM);
            TDCACM.FUNC = 'R';
            TDCACM.AC_NO = TDCPSWDR.AC_NO;
            TDCACM.OLD_AC_NO = TDCPSWDR.AC_NO;
            TDCACM.CARD_PSW_OLD = TDCPSWDR.PSW_N;
            TDCACM.CARD_PSW_NEW = TDCPSWDR.PSW_N;
            TDCACM.ID_TYP = TDCPSWDR.ID_TYP;
            TDCACM.ID_NO = TDCPSWDR.ID_NO;
            TDCACM.CI_NM = CICCUST.O_DATA.O_CI_NM;
            S000_CALL_TDZACM();
        }
    }
    public void B250_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P505";
        BPCPNHIS.INFO.AC = TDCPSWDR.AC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = CICACCU.DATA.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
    }
    public void B300_FMT_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOPSRI);
        TDCOPSRI.BV_TYP = TDCPSWDR.BV_TYP;
        TDCOPSRI.BV_NO = TDCPSWDR.BV_NO;
        TDCOPSRI.AC_NO = TDCPSWDR.AC_NO;
        TDCOPSRI.AC_NM = TDCPSWDR.AC_NM;
        TDCOPSRI.ID_TYP = TDCPSWDR.ID_TYP;
        TDCOPSRI.ID_NO = TDCPSWDR.ID_NO;
        TDCOPSRI.EFF_DT = WS_EFF_DT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_PSWDR_I_FMT;
        SCCFMT.DATA_PTR = TDCOPSRI;
        SCCFMT.DATA_LEN = 389;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_GEN_PSW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCHMPW);
        SCCHMPW.INP_DATA.SERV_ID = "E143";
        SCCHMPW.INP_DATA.AC_FLG = '0';
        SCCHMPW.INP_DATA.OLD_AC = TDCPSWDR.AC_NO;
        SCCHMPW.INP_DATA.NEW_AC = TDCPSWDR.AC_NO;
        SCCHMPW.INP_DATA.PIN_DA = WS_PSW;
        if (TDCPSWDR.BV_TYP == '7') {
            SCCHMPW.INP_DATA.OLD_AC = WS_SUB_AC;
            SCCHMPW.INP_DATA.NEW_AC = WS_SUB_AC;
        }
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.OLD_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.NEW_AC);
        CEP.TRC(SCCGWA, SCCHMPW.INP_DATA.PIN_DA);
        S000_CALL_SCZHMPW();
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW);
        WS_PSW = SCCHMPW.OUT_INFO.OUT_DATA.OUT_PW;
    }
    public void R000_CHK_AC_BAL() throws IOException,SQLException,Exception {
    }
    public void R000_CHK_ID_TYP() throws IOException,SQLException,Exception {
        if (!TDCPSWDR.ID_TYP.equalsIgnoreCase("10100") 
            && !TDCPSWDR.ID_TYP.equalsIgnoreCase("10200")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_IDTYP_3DAYS_LIMIT;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZACM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-PSW-CHECK", TDCACM);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZUPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-UNIT-TDPARM", TDCUPARM);
    }
    public void S000_CALL_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI  ", CICSSCH);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCZHMPW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCHMPW);
        IBS.CALLCPN(SCCGWA, "SC-HM-PASSWORD", SCCHMPW);
        CEP.TRC(SCCGWA, SCCHMPW.OUT_INFO.ERR_CODE);
        if (!SCCHMPW.OUT_INFO.ERR_CODE.equalsIgnoreCase("SC0000")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_PWD_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC_MSG.WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        WS_RC_MSG.WS_AP_MMO = "SC";
        if (WS_RC_MSG.WS_RC != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, WS_RC_MSG);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CISOACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S00_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_ERR_INFO);
    }
    public void T000_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void T000_READ_TDTCMST_Y() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READNEXT_BVT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_DB_FLG = 'N';
        } else {
            WS_BVT_DB_FLG = 'Y';
        }
    }
    public void T000_STARTBR_BVT() throws IOException,SQLException,Exception {
        TDTBVT_BR.rp = new DBParm();
        TDTBVT_BR.rp.TableName = "TDTBVT";
        TDTBVT_BR.rp.upd = true;
        TDTBVT_BR.rp.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.STARTBR(SCCGWA, TDRBVT, this, TDTBVT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READNEXT_TSTS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSTS, this, TDTSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TSTS_DB_FLG = 'N';
        } else {
            WS_TSTS_DB_FLG = 'Y';
        }
    }
    public void T000_STARTBR_TSTS() throws IOException,SQLException,Exception {
        TDTSTS_BR.rp = new DBParm();
        TDTSTS_BR.rp.TableName = "TDTSTS";
        TDTSTS_BR.rp.upd = true;
        TDTSTS_BR.rp.where = "AC_NO = :TDRSTS.KEY.AC_NO "
            + "AND TYPE = :TDRSTS.KEY.TYPE "
            + "AND POS = :TDRSTS.KEY.POS "
            + "AND STS = :TDRSTS.STS";
        IBS.STARTBR(SCCGWA, TDRSTS, this, TDTSTS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_REWRITE_TDTSTS() throws IOException,SQLException,Exception {
        TDTSTS_RD = new DBParm();
        TDTSTS_RD.TableName = "TDTSTS";
        IBS.REWRITE(SCCGWA, TDRSTS, TDTSTS_RD);
    }
    public void T000_UPDATE_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRBVT.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.REWRITE(SCCGWA, TDRBVT, TDTBVT_RD);
    }
    public void T000_UPDATE_TDTCMST() throws IOException,SQLException,Exception {
        TDRCMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        TDRCMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.REWRITE(SCCGWA, TDRCMST, TDTCMST_RD);
    }
    public void S000_CALL_DCCUIQMC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-INQ-VA-AC", DCCUIQMC);
    }
    public void T000_READ_BVT() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void S000_CALL_DCCIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
    }
    public void T000_READ_RGTLOSS() throws IOException,SQLException,Exception {
    }
    public void T000_STARTBR_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_BR.rp = new DBParm();
        DCTIACCY_BR.rp.TableName = "DCTIACCY";
        DCTIACCY_BR.rp.where = "VIA_AC = :DCRIACCY.KEY.VIA_AC";
        IBS.STARTBR(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
    }
    public void T000_READNEXT_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIACCY, this, DCTIACCY_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTIACCY_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTIACCY_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
        }
    }
    public void T000_ENDBR_DCTIACCY() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIACCY_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
