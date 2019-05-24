package com.hisun.DD;

import com.hisun.DC.*;
import com.hisun.TD.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;
import com.hisun.CI.*;
import com.hisun.DB.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSOZM {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTCCZM_RD;
    DBParm DDTZMAC_RD;
    DBParm DCTHLD_RD;
    DBParm DDTCCY_RD;
    boolean pgmRtn = false;
    String WS_HLD_AC = " ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    int WS_AC_CNT = 0;
    String WS_SUB_AC = " ";
    double WS_AVA_BAL = 0;
    int WS_I = 0;
    String K_FMT_CODE = "DD152";
    char WS_DDTCCZM_REC = ' ';
    char WS_DCTHLD_REC = ' ';
    char WS_AUTH_FLG = ' ';
    char WS_DD_AC_HLD_FLG = ' ';
    char WS_DD_AC_FBD_FLG = ' ';
    char WS_TD_AC_HLD_FLG = ' ';
    char WS_TD_AC_FBD_FLG = ' ';
    char WS_TD_AC_PLG_FLG = ' ';
    int WS_S = 0;
    int WS_B = 0;
    int WS_LIST_CNT = 0;
    DDZSOZM_WS_AC_LIST[] WS_AC_LIST = new DDZSOZM_WS_AC_LIST[20];
    double WS_USD_BAL = 0;
    double WS_USD_CBAL = 0;
    double WS_TOTAL_AMT = 0;
    DDZSOZM_WS_REF_NO WS_REF_NO = new DDZSOZM_WS_REF_NO();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DCCPACTY DCCPACTY = new DCCPACTY();
    DDCSCINM DDCSCINM = new DDCSCINM();
    DCRHLD DCRHLD = new DCRHLD();
    DDRCCZM DDRCCZM = new DDRCCZM();
    DDRZMAC DDRZMAC = new DDRZMAC();
    DCCUHLD DCCUHLD = new DCCUHLD();
    TDCACE TDCACE = new TDCACE();
    DDCSIBAL DDCSIBAL = new DDCSIBAL();
    DCCIQBAL DCCIQBAL = new DCCIQBAL();
    DDCSZMO DDCSZMO = new DDCSZMO();
    DCCUABRW DCCUABRW = new DCCUABRW();
    BPCEX BPCEX = new BPCEX();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICCUST CICCUST = new CICCUST();
    CICSAGEN CICSAGEN = new CICSAGEN();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSOBAL BPCSOBAL = new BPCSOBAL();
    DDRCCY DDRCCY = new DDRCCY();
    CICQACAC CICQACAC = new CICQACAC();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCUHOLD DCCUHOLD = new DCCUHOLD();
    int WS_MAX_SEQ = 0;
    int WS_HLD_CNT = 0;
    int WS_MST_CNT = 0;
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    DDCSOZM DDCSOZM;
    CICGAGA_AGENT_AREA CICGAGA_AGENT_AREA;
    public DDZSOZM() {
        for (int i=0;i<20;i++) WS_AC_LIST[i] = new DDZSOZM_WS_AC_LIST();
    }
    public void MP(SCCGWA SCCGWA, DDCSOZM DDCSOZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSOZM = DDCSOZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSOZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        CICGAGA_AGENT_AREA = new CICGAGA_AGENT_AREA_AGENT_AREA();
        IBS.init(SCCGWA, CICGAGA_AGENT_AREA);
        IBS.CPY2CLS(SCCGWA, SCCGWA.AGENT_AREA_PTR, CICGAGA_AGENT_AREA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_EXPD);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_DATE);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (DDCSOZM.INPUT_DATA.IZM_FUNC == '1') {
            K_FMT_CODE = "DD152";
            B030_OPEN_PERSONAL_CCZM();
            if (pgmRtn) return;
        } else {
            if (DDCSOZM.INPUT_DATA.IZM_FUNC == '2') {
                K_FMT_CODE = "DD156";
                B050_OPEN_COM_CCZM();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            B230_WRI_NFIN_HIS_PROC();
            if (pgmRtn) return;
        }
        if (SCCGWA.COMM_AREA.AGENT_FLG == 'Y') {
            B080_AGENT_INFO_PROC();
            if (pgmRtn) return;
        }
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        B010_07_CHECK_CI_STS();
        if (pgmRtn) return;
        B010_01_CHECK_COMMON_INPUT();
        if (pgmRtn) return;
        if (DDCSOZM.INPUT_DATA.IZM_FUNC == '1') {
            B010_03_CHECK_PERSONAL();
            if (pgmRtn) return;
        } else {
            B010_05_CHECK_COMPANY();
            if (pgmRtn) return;
        }
    }
    public void B010_07_CHECK_CI_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'C';
        CICCUST.DATA.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            if (DDCSOZM.INPUT_DATA.IZM_FUNC == '2' 
                && DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_GRP_FLG == '2' 
                && DDCSOZM.INPUT_DATA.IZM_BAL_EXPD <= SCCGWA.COMM_AREA.AC_DATE) {
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CI_CLOSE;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (CICCUST.O_DATA.O_STSW == null) CICCUST.O_DATA.O_STSW = "";
        JIBS_tmp_int = CICCUST.O_DATA.O_STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CICCUST.O_DATA.O_STSW += " ";
        if (CICCUST.O_DATA.O_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_CI_FBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_OPEN_PERSONAL_CCZM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "********* TRC-BAL-TYPE *********************");
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_TYPE);
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '1') {
            B030_01_PERSONAL_HOLD_PROC();
            if (pgmRtn) return;
        }
        B030_03_PERSONAL_ADD_CCZM();
        if (pgmRtn) return;
    }
    public void B030_01_PERSONAL_HOLD_PROC() throws IOException,SQLException,Exception {
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            CEP.TRC(SCCGWA, "F-HLD-PRC");
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT);
            IBS.init(SCCGWA, DCCUHOLD);
            DCCUHOLD.DATA.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            DCCUHOLD.DATA.SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
            DCCUHOLD.DATA.HLD_TYP = '2';
            DCCUHOLD.DATA.SPR_TYP = '2';
            DCCUHOLD.DATA.HLD_CLS = '3';
            DCCUHOLD.DATA.HLD_FLG = '1';
            DCCUHOLD.DATA.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
            DCCUHOLD.DATA.CCY_TYP = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
            DCCUHOLD.DATA.AMT = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT;
            DCCUHOLD.DATA.EXP_DT = DDCSOZM.INPUT_DATA.IZM_BAL_EXPD;
            DCCUHOLD.DATA.RSN = "�?立存款证�?";
            CEP.TRC(SCCGWA, DCCUHOLD.DATA.AC);
            CEP.TRC(SCCGWA, DCCUHOLD.DATA.CCY);
            CEP.TRC(SCCGWA, DCCUHOLD.DATA.CCY_TYP);
            CEP.TRC(SCCGWA, DCCUHOLD.DATA.AMT);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_EXPD);
            S000_CALL_DCZUHOLD();
            if (pgmRtn) return;
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_STS = 'N';
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_NO = DCCUHOLD.DATA.HLD_NO;
        }
    }
    public void B030_03_PERSONAL_ADD_CCZM() throws IOException,SQLException,Exception {
        R000_GET_MAX_REF_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_CNT);
        for (WS_CNT = 1; WS_CNT <= DDCSOZM.INPUT_DATA.IZM_BV_CNT; WS_CNT += 1) {
            IBS.init(SCCGWA, DDRCCZM);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_CI_NO);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_OPEN_CNT);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_EN_TLE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_DATE);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_EXPD);
            DDRCCZM.KEY.OPEN_BV_CODE = DDCSOZM.OUTPUT_DATA.OZM_AC_LIST[1-1].OZM_AC_BV_CD;
            DDRCCZM.KEY.OPEN_BV = DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV;
            DDRCCZM.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
            DDRCCZM.STS = 'N';
            DDRCCZM.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
            DDRCCZM.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
            DDRCCZM.OPEN_CNT = DDCSOZM.INPUT_DATA.IZM_OPEN_CNT;
            DDRCCZM.TOTAL_OPEN_AMT = WS_TOTAL_AMT;
            DDRCCZM.CH_TLE = DDCSOZM.INPUT_DATA.IZM_CH_TLE;
            DDRCCZM.EN_TLE = DDCSOZM.INPUT_DATA.IZM_EN_TLE;
            DDRCCZM.EN_NAME = DDCSOZM.INPUT_DATA.IZM_EN_NAME;
            DDRCCZM.BAL_TYPE = DDCSOZM.INPUT_DATA.IZM_BAL_TYPE;
            DDRCCZM.BAL_DATE = DDCSOZM.INPUT_DATA.IZM_BAL_DATE;
            DDRCCZM.BAL_EXPD = DDCSOZM.INPUT_DATA.IZM_BAL_EXPD;
            DDRCCZM.BANK_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRCCZM.RMK = DDCSOZM.INPUT_DATA.IZM_RMK;
            DDRCCZM.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCZM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_WRITE_DDTCCZM();
            if (pgmRtn) return;
            B200_BV_USE();
            if (pgmRtn) return;
        }
        B030_031_ADD_ZMAC();
        if (pgmRtn) return;
    }
    public void B030_031_ADD_ZMAC() throws IOException,SQLException,Exception {
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            IBS.init(SCCGWA, DDRZMAC);
            CEP.TRC(SCCGWA, WS_REF_NO);
            DDRZMAC.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
            DDRZMAC.KEY.SEQ = WS_AC_CNT;
            DDRZMAC.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            DDRZMAC.AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
            DDRZMAC.AC_BV_CD = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_CD;
            DDRZMAC.AC_BV_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_NO;
            DDRZMAC.HLD_STS = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_STS;
            DDRZMAC.HLD_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_NO;
            DDRZMAC.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
            DDRZMAC.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
            DDRZMAC.OPEN_AMT = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT;
            DDRZMAC.AC_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_TYP;
            DDRZMAC.VAL_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_VAL_DATE;
            DDRZMAC.EXP_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_EXP_DATE;
            DDRZMAC.BANK_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRZMAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_WRITE_DDTZMAC();
            if (pgmRtn) return;
        }
    }
    public void B010_01_CHECK_COMMON_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_CI_NO);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_OPEN_CNT);
        if (DDCSOZM.INPUT_DATA.IZM_CI_NO.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_CI_NULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSOZM.INPUT_DATA.IZM_OPEN_CNT == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_NUM_M_INVLID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_GRP_FLG);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_DEF_FLG);
            if (DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_GRP_FLG == ' ') {
                DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_GRP_FLG = '2';
            }
            if (DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_DEF_FLG == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_CI_NULL;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_03_CHECK_PERSONAL() throws IOException,SQLException,Exception {
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            CICQACAC.DATA.AGR_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
            CICQACAC.DATA.CCY_ACAC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
            CICQACAC.DATA.CR_FLG = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            if (!CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_CI_NO)) {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.SOZM_IZM_CI_NO_SAME);
            }
            if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
                B010_033_PERSONAL_DC_PROC();
                if (pgmRtn) return;
            } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
                B010_031_PERSONAL_DD_PROC();
                if (pgmRtn) return;
            } else if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
                B010_032_PERSONAL_TD_PROC();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_JSZM_AC_NOT_EXIST);
            }
            WS_TOTAL_AMT = WS_TOTAL_AMT + DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT;
        }
    }
    public void B010_033_PERSONAL_DC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-PERSONAL-DC");
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_APP);
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            B010_031_PERSONAL_DD_PROC();
            if (pgmRtn) return;
        } else {
            if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
                B010_032_PERSONAL_TD_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_031_PERSONAL_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        CICQACAC.DATA.AGR_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        CICQACAC.DATA.CR_FLG = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIQPRD);
        IBS.init(SCCGWA, DDVMPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = CICQACAC.O_DATA.O_ACAC_DATA.O_PROD_CD_ACAC;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
        if (DDVMPRD.VAL.CERT_FLG == 'N') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_ALLOW_PERIOD_CER);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, DDCSCINM);
            DDCSCINM.INPUT_DATA.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            DDCSCINM.INPUT_DATA.FUNC = '2';
            S000_CALL_DDZSCINM();
            if (pgmRtn) return;
            if (DDCSCINM.OUTPUT_DATA.AC_STS == 'C') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD == null) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD == null) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD = "";
            JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD += " ";
            if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDRCCY.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        DDRCCY.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE);
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDCSIBAL);
        DDCSIBAL.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDCSIBAL.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        DDCSIBAL.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        DDCSIBAL.FUNC = 'C';
        S000_CALL_DDZSIBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "FIND BUG4");
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSIBAL.FUNC);
        CEP.TRC(SCCGWA, DDCSIBAL.CURR_BAL);
        CEP.TRC(SCCGWA, DDCSIBAL.AVL_BAL);
        if (DDCSIBAL.FUNC == '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAL_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = DDCSIBAL.CURR_BAL;
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AVAL_BAL = DDCSIBAL.AVL_BAL;
            if (DDCSIBAL.AVL_BAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
                WS_AUTH_FLG = 'Y';
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AUTH_WHEN_AMT_NOT_ENH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSIBAL.CURR_BAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_032_PERSONAL_TD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        TDCACE.PAGE_INF.I_AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        if (TDCACE.PAGE_INF.AC_STS == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PASSWD_LOCK_CNOT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
        if (TDCACE.DATA[1-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ALREADY_AC_HLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
        if (TDCACE.DATA[1-1].ACO_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ALREADY_AC_HLD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
        if (TDCACE.DATA[1-1].ACO_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
        if (TDCACE.DATA[1-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
        if (TDCACE.DATA[1-1].ACO_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_TDAC_STS_PLG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_FROZEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_AVA_BAL = TDCACE.DATA[1-1].BAL - TDCACE.DATA[1-1].HBAL;
        CEP.TRC(SCCGWA, WS_AC_CNT);
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = TDCACE.DATA[1-1].BAL;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AVAL_BAL = WS_AVA_BAL;
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT);
        if (WS_AVA_BAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
            WS_AUTH_FLG = 'Y';
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AUTH_WHEN_AMT_NOT_ENH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].BAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAL_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_05_CHECK_COMPANY() throws IOException,SQLException,Exception {
        for (WS_AC_CNT = 1; WS_AC_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_AC_CNT += 1) {
            B010_053_COMPANY_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "FIND BUG53");
            B010_055_MOVE_TO_WS_LIST();
            if (pgmRtn) return;
        }
    }
    public void B010_051_COMPANY_SUM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.FUNC = '1';
        DCCPACTY.INPUT.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        S000_CALL_DDZPACTY();
        if (pgmRtn) return;
        B010_051_03_CHECK_DUPLICATE_AC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.AC_TYPE);
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.VIA_AC);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("DD")) {
            B010_053_01_COMPANY_DD_PROC();
            if (pgmRtn) return;
        } else if (DCCPACTY.OUTPUT.AC_TYPE == 'D' 
                && DCCPACTY.OUTPUT.AC_DETL.equalsIgnoreCase("TD")) {
            B010_053_03_COMPANY_TD_PROC();
            if (pgmRtn) return;
        } else if ((DCCPACTY.OUTPUT.AC_TYPE == 'K' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'I' 
                || DCCPACTY.OUTPUT.AC_TYPE == 'V')) {
            B010_051_01_SUM_INQ();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_051_03_CHECK_DUPLICATE_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUABRW);
        DCCUABRW.INP_DATA.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DCCUABRW.INP_DATA.ROWS = 200;
        DCCUABRW.INP_DATA.PAGE_NUM = 1;
        S000_CALL_DCZUABRW();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUABRW.OUT_PAGE.TOTAL_NUM);
        for (WS_S = 1; WS_S <= DCCUABRW.OUT_PAGE.TOTAL_NUM; WS_S += 1) {
            for (WS_B = 1; WS_B <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_B += 1) {
                CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_AC);
                if (DCCUABRW.OUT_DATA[WS_S-1].SUB_AC.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_AC) 
                    && DCCUABRW.OUT_DATA[WS_S-1].AC_CCY.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_CCY) 
                    && DCCUABRW.OUT_DATA[WS_S-1].CCY_TYPE == DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_CCY_TYPE) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (DCCUABRW.OUT_DATA[WS_S-1].SUB_AC.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_AC) 
                    && DCCUABRW.OUT_DATA[WS_S-1].VCH_NO.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_B-1].IZM_AC_BV_NO)) {
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_GET_MAX_REF_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        IBS.init(SCCGWA, WS_REF_NO);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "CCZM";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_REF_NO.WS_YEAR = 0;
        else WS_REF_NO.WS_YEAR = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_REF_NO.WS_SEQ = BPCSGSEQ.SEQ;
        CEP.TRC(SCCGWA, WS_REF_NO);
    }
    public void R000_CHECK_VA_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, "YYY");
        if (DCCIMSTR.DATA.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        if (DCCIMSTR.DATA.STS_WORD.substring(0, 1).equalsIgnoreCase("1")) {
            DDCSZMO.AC_LIST[WS_AC_CNT-1].HLD_TYP = '1';
        }
        CEP.TRC(SCCGWA, "YYX");
    }
    public void B010_051_01_SUM_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        if (DCCPACTY.OUTPUT.AC_TYPE == 'K') {
            DCCIMSTR.KEY.VIA_AC = DCCPACTY.OUTPUT.VIA_AC;
        } else {
            DCCIMSTR.KEY.VIA_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        }
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD);
        if (DCCIMSTR.DATA.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        if (DCCIMSTR.DATA.STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AC_LIST[WS_AC_CNT-1].WS_FBID_TYP = '1';
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        if (DCCIMSTR.DATA.STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AC_LIST[WS_AC_CNT-1].WS_FBID_TYP = '2';
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD.substring(2 - 1, 2 + 1 - 1));
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD.substring(5 - 1, 5 + 1 - 1));
        IBS.init(SCCGWA, DCCIQBAL);
        DCCIQBAL.INP_DATA.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DCCIQBAL.INP_DATA.FLG = '1';
        S000_CALL_DCZIQBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, WS_LIST_CNT);
        for (WS_I = 1; WS_I <= 99 
            && WS_LIST_CNT <= 20; WS_I += 1) {
            if (DCCIQBAL.OUT_DATA[WS_I-1].CCY_BAL > 0 
                && DCCIQBAL.OUT_DATA[WS_I-1].CCY.equalsIgnoreCase("156")) {
                WS_LIST_CNT = WS_LIST_CNT + 1;
                WS_AC_LIST[WS_LIST_CNT-1].WS_AC = DCCIQBAL.INP_DATA.AC;
                WS_AC_LIST[WS_LIST_CNT-1].WS_CCY = DCCIQBAL.OUT_DATA[WS_I-1].CCY;
                WS_AC_LIST[WS_LIST_CNT-1].WS_CCY_TYPE = DCCIQBAL.OUT_DATA[WS_I-1].CCY_TYPE;
                WS_AC_LIST[WS_LIST_CNT-1].WS_AVL_BAL = DCCIQBAL.OUT_DATA[WS_I-1].AVL_BAL;
                WS_AC_LIST[WS_LIST_CNT-1].WS_CUR_BAL = DCCIQBAL.OUT_DATA[WS_I-1].CCY_BAL;
                WS_AC_LIST[WS_LIST_CNT-1].WS_HLD_AMT = DCCIQBAL.OUT_DATA[WS_I-1].HLD_TOT_BAL;
                CEP.TRC(SCCGWA, WS_AC_LIST[WS_LIST_CNT-1].WS_CCY);
                CEP.TRC(SCCGWA, WS_AC_LIST[WS_LIST_CNT-1].WS_AVL_BAL);
                CEP.TRC(SCCGWA, WS_AC_LIST[WS_LIST_CNT-1].WS_CUR_BAL);
                CEP.TRC(SCCGWA, WS_AC_LIST[WS_LIST_CNT-1].WS_HLD_AMT);
                CEP.TRC(SCCGWA, WS_AC_LIST[WS_LIST_CNT-1].WS_HLD_AMT);
            } else {
                if (DCCIQBAL.OUT_DATA[WS_I-1].AVL_BAL > 0 
                    && !DCCIQBAL.OUT_DATA[WS_I-1].CCY.equalsIgnoreCase("156")) {
                    CEP.TRC(SCCGWA, DCCIQBAL.OUT_DATA[WS_I-1].CCY);
                    CEP.TRC(SCCGWA, DCCIQBAL.OUT_DATA[WS_I-1].CCY_TYPE);
                    CEP.TRC(SCCGWA, DCCIQBAL.OUT_DATA[WS_I-1].AVL_BAL);
                    CEP.TRC(SCCGWA, DCCIQBAL.OUT_DATA[WS_I-1].CCY_BAL);
                    IBS.init(SCCGWA, BPCEX);
                    BPCEX.BUY_CCY = DCCIQBAL.OUT_DATA[WS_I-1].CCY;
                    BPCEX.BUY_AMT = DCCIQBAL.OUT_DATA[WS_I-1].AVL_BAL;
                    BPCEX.SELL_CCY = "840";
                    S00_LINK_BPZEX();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCEX.SELL_AMT);
                    CEP.TRC(SCCGWA, BPCEX.TRN_EX_RATE);
                    WS_USD_BAL = WS_USD_BAL + BPCEX.SELL_AMT;
                    IBS.init(SCCGWA, BPCEX);
                    BPCEX.BUY_CCY = DCCIQBAL.OUT_DATA[WS_I-1].CCY;
                    BPCEX.BUY_AMT = DCCIQBAL.OUT_DATA[WS_I-1].CCY_BAL;
                    BPCEX.SELL_CCY = "840";
                    S00_LINK_BPZEX();
                    if (pgmRtn) return;
                    WS_USD_CBAL = WS_USD_CBAL + BPCEX.SELL_AMT;
                }
            }
        }
        if (WS_USD_BAL != 0) {
            WS_LIST_CNT = WS_LIST_CNT + 1;
            WS_AC_LIST[WS_LIST_CNT-1].WS_AC = DCCIQBAL.INP_DATA.AC;
            WS_AC_LIST[WS_LIST_CNT-1].WS_CCY = "840";
            WS_AC_LIST[WS_LIST_CNT-1].WS_AVL_BAL = WS_USD_BAL;
            WS_AC_LIST[WS_LIST_CNT-1].WS_CUR_BAL = WS_USD_CBAL;
        }
    }
    public void B010_053_COMPANY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        CICQACAC.DATA.AGR_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        CICQACAC.DATA.CCY_ACAC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        CICQACAC.DATA.CR_FLG = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.DATA.AGR_SEQ);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CCY_ACAC);
        CEP.TRC(SCCGWA, CICQACAC.DATA.CR_FLG);
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (!CICQACAC.O_DATA.O_ACR_DATA.O_CI_NO.equalsIgnoreCase(DDCSOZM.INPUT_DATA.IZM_CI_NO)) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.SOZM_IZM_CI_NO_SAME);
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DC")) {
            T000_CHECK_DCAC_STS();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            T000_CHECK_DDAC_STS();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("TD")) {
            T000_CHECK_TDAC_STS();
            if (pgmRtn) return;
        }
        if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("DD")) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            CICQACAC.DATA.AGR_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
            CICQACAC.DATA.CCY_ACAC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
            CICQACAC.DATA.CR_FLG = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCIQPRD);
            IBS.init(SCCGWA, DDVMPRD);
            DDCIQPRD.INPUT_DATA.PROD_CODE = CICQACAC.O_DATA.O_ACR_DATA.O_PROD_CD_ACR;
            CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
            DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
            DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
            S000_CALL_DDZIQPRD();
            if (pgmRtn) return;
            if (DDVMPRD.VAL.CERT_FLG == 'N') {
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NOT_ALLOW_PERIOD_CER);
            }
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_TYP = '3';
            CEP.TRC(SCCGWA, "XXX1");
            if (DDCSOZM.INPUT_DATA.IZM_BAL_EXPD < SCCGWA.COMM_AREA.AC_DATE) {
                B800_GET_BAL_DATE_BAL();
                if (pgmRtn) return;
            } else {
                B010_053_01_COMPANY_DD_PROC();
                if (pgmRtn) return;
            }
        } else if (CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC.equalsIgnoreCase("TD")) {
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_TYP = '4';
            CEP.TRC(SCCGWA, "XXX4");
            if (DDCSOZM.INPUT_DATA.IZM_BAL_EXPD < SCCGWA.COMM_AREA.AC_DATE) {
                CEP.TRC(SCCGWA, "XXX3");
                B800_GET_BAL_DATE_BAL();
                if (pgmRtn) return;
            } else {
                CEP.TRC(SCCGWA, "XXX6");
                B010_053_03_COMPANY_TD_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_TOTAL_AMT = WS_TOTAL_AMT + DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT;
        CEP.TRC(SCCGWA, WS_TOTAL_AMT);
    }
    public void B010_055_MOVE_TO_WS_LIST() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-MOVE-WS");
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_NO);
        WS_LIST_CNT = WS_LIST_CNT + 1;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AC_BV_CD = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_CD;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AC_BV_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_NO;
        WS_AC_LIST[WS_LIST_CNT-1].WS_CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        WS_AC_LIST[WS_LIST_CNT-1].WS_CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        WS_AC_LIST[WS_LIST_CNT-1].WS_OPEN_AMT = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT;
        WS_AC_LIST[WS_LIST_CNT-1].WS_VAL_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_VAL_DATE;
        WS_AC_LIST[WS_LIST_CNT-1].WS_EXP_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_EXP_DATE;
        WS_AC_LIST[WS_LIST_CNT-1].WS_CUR_BAL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AVL_BAL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AVAL_BAL;
        WS_AC_LIST[WS_LIST_CNT-1].WS_HLD_AMT = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_AMT;
        WS_AC_LIST[WS_LIST_CNT-1].WS_AC_TYP = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_TYP;
        CEP.TRC(SCCGWA, WS_LIST_CNT);
    }
    public void B010_053_01_COMPANY_DD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'M') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_M;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1));
        IBS.init(SCCGWA, DDCSIBAL);
        DDCSIBAL.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDCSIBAL.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        DDCSIBAL.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        DDCSIBAL.FUNC = 'C';
        S000_CALL_DDZSIBAL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSIBAL.FUNC);
        if (DDCSIBAL.FUNC == '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAL_NOT_ENOUGH;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = DDCSIBAL.CURR_BAL;
            CEP.TRC(SCCGWA, DDCSIBAL.CURR_BAL);
            CEP.TRC(SCCGWA, DDCSIBAL.AVL_BAL);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AVAL_BAL);
            if (DDCSIBAL.AVL_BAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_BAL_NOT_ENOUGH;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_053_02_CAL_ZHINENG_BAL() throws IOException,SQLException,Exception {
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        CEP.TRC(SCCGWA, DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(44 - 1, 44 + 1 - 1));
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC);
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDRCCY.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        DDRCCY.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL + DDRCCY.CCAL_TOT_BAL;
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL);
    }
    public void B010_053_03_COMPANY_TD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        TDCACE.PAGE_INF.I_AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        TDCACE.PAGE_INF.I_BV_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_BV_NO;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        if (TDCACE.DATA[1-1].PBAL < DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_OPEN_AMT) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_OP_BAL_E;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.PAGE_INF.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_AC_LIST[WS_AC_CNT-1].WS_FBID_TYP = '1';
        } else {
            if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
            JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
            if (TDCACE.PAGE_INF.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_AC_LIST[WS_AC_CNT-1].WS_FBID_TYP = '2';
            }
        }
        WS_AVA_BAL = TDCACE.DATA[1-1].BAL - TDCACE.DATA[1-1].HBAL;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = TDCACE.DATA[1-1].BAL;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AVAL_BAL = WS_AVA_BAL;
        CEP.TRC(SCCGWA, "CANCEL-CHECK-AVA-BAL");
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_VAL_DATE = TDCACE.DATA[1-1].SDT;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_EXP_DATE = TDCACE.DATA[1-1].DDT;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY = TDCACE.DATA[1-1].CCY;
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE = TDCACE.DATA[1-1].CCY_TYP;
    }
    public void B050_OPEN_COM_CCZM() throws IOException,SQLException,Exception {
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '1') {
            B030_01_PERSONAL_HOLD_PROC();
            if (pgmRtn) return;
        }
        R000_GET_MAX_REF_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_CNT);
        for (WS_CNT = 1; WS_CNT <= DDCSOZM.INPUT_DATA.IZM_BV_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, DDCSOZM.OUTPUT_DATA.OZM_AC_LIST[1-1].OZM_AC_BV_CD);
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV);
            CEP.TRC(SCCGWA, WS_REF_NO);
            IBS.init(SCCGWA, DDRCCZM);
            DDRCCZM.KEY.OPEN_BV_CODE = DDCSOZM.OUTPUT_DATA.OZM_AC_LIST[1-1].OZM_AC_BV_CD;
            DDRCCZM.KEY.OPEN_BV = DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV;
            DDRCCZM.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
            DDRCCZM.STS = 'N';
            DDRCCZM.STSW = "ZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZEROZERO";
            if (DDRCCZM.STSW == null) DDRCCZM.STSW = "";
            JIBS_tmp_int = DDRCCZM.STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCZM.STSW += " ";
            DDRCCZM.STSW = "1" + DDRCCZM.STSW.substring(1);
            DDRCCZM.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
            DDRCCZM.OPEN_CNT = DDCSOZM.INPUT_DATA.IZM_OPEN_CNT;
            DDRCCZM.TOTAL_OPEN_AMT = WS_TOTAL_AMT;
            DDRCCZM.CH_TLE = DDCSOZM.INPUT_DATA.IZM_CH_TLE;
            DDRCCZM.EN_TLE = DDCSOZM.INPUT_DATA.IZM_EN_TLE;
            DDRCCZM.EN_NAME = DDCSOZM.INPUT_DATA.IZM_EN_NAME;
            DDRCCZM.BAL_TYPE = DDCSOZM.INPUT_DATA.IZM_BAL_TYPE;
            DDRCCZM.BAL_DATE = DDCSOZM.INPUT_DATA.IZM_BAL_DATE;
            DDRCCZM.BAL_EXPD = DDCSOZM.INPUT_DATA.IZM_BAL_EXPD;
            DDRCCZM.BANK_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRCCZM.RMK = DDCSOZM.INPUT_DATA.IZM_RMK;
            DDRCCZM.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCZM.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_WRITE_DDTCCZM();
            if (pgmRtn) return;
            B200_BV_USE();
            if (pgmRtn) return;
        }
        B050_01_COMPANY_ADD_ZMAC();
        if (pgmRtn) return;
    }
    public void B050_01_COMPANY_ADD_ZMAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_REF_NO);
        for (WS_AC_CNT = 1; WS_AC_CNT <= WS_LIST_CNT; WS_AC_CNT += 1) {
            IBS.init(SCCGWA, DDRZMAC);
            DDRZMAC.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
            CEP.TRC(SCCGWA, WS_AC_CNT);
            DDRZMAC.KEY.SEQ = WS_AC_CNT;
            DDRZMAC.AC = WS_AC_LIST[WS_AC_CNT-1].WS_AC;
            DDRZMAC.AC_SEQ = WS_AC_LIST[WS_AC_CNT-1].WS_AC_SEQ;
            DDRZMAC.AC_BV_CD = WS_AC_LIST[WS_AC_CNT-1].WS_AC_BV_CD;
            DDRZMAC.AC_BV_NO = WS_AC_LIST[WS_AC_CNT-1].WS_AC_BV_NO;
            DDRZMAC.CCY = WS_AC_LIST[WS_AC_CNT-1].WS_CCY;
            DDRZMAC.HLD_STS = 'N';
            DDRZMAC.HLD_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_HLD_NO;
            DDRZMAC.CCY_TYPE = WS_AC_LIST[WS_AC_CNT-1].WS_CCY_TYPE;
            DDRZMAC.OPEN_AMT = WS_AC_LIST[WS_AC_CNT-1].WS_OPEN_AMT;
            DDRZMAC.STSW = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            if (WS_DD_AC_HLD_FLG == 'Y' 
                || WS_TD_AC_HLD_FLG == 'Y') {
                if (DDRZMAC.STSW == null) DDRZMAC.STSW = "";
                JIBS_tmp_int = DDRZMAC.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRZMAC.STSW += " ";
                DDRZMAC.STSW = "1" + DDRZMAC.STSW.substring(1);
            }
            if (WS_DD_AC_FBD_FLG == 'Y' 
                || WS_TD_AC_FBD_FLG == 'Y') {
                if (DDRZMAC.STSW == null) DDRZMAC.STSW = "";
                JIBS_tmp_int = DDRZMAC.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRZMAC.STSW += " ";
                DDRZMAC.STSW = DDRZMAC.STSW.substring(0, 2 - 1) + "1" + DDRZMAC.STSW.substring(2 + 1 - 1);
            }
            if (WS_TD_AC_PLG_FLG == 'Y') {
                if (DDRZMAC.STSW == null) DDRZMAC.STSW = "";
                JIBS_tmp_int = DDRZMAC.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) DDRZMAC.STSW += " ";
                DDRZMAC.STSW = DDRZMAC.STSW.substring(0, 3 - 1) + "1" + DDRZMAC.STSW.substring(3 + 1 - 1);
            }
            DDRZMAC.AC_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_TYP;
            DDRZMAC.VAL_DATE = WS_AC_LIST[WS_AC_CNT-1].WS_VAL_DATE;
            DDRZMAC.EXP_DATE = WS_AC_LIST[WS_AC_CNT-1].WS_EXP_DATE;
            DDRZMAC.BANK_NO = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRZMAC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMAC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRZMAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRZMAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T00_WRITE_DDTZMAC();
            if (pgmRtn) return;
        }
    }
    public void B080_AGENT_INFO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSAGEN);
        CICSAGEN.FUNC = 'A';
        CICSAGEN.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        CICSAGEN.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CICSAGEN.TX_CODE = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CICSAGEN.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CICSAGEN.TLR_NO = SCCGWA.COMM_AREA.TL_ID;
        CICSAGEN.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
        CICSAGEN.ID_TYP = CICGAGA_AGENT_AREA.ID_TYP;
        CICSAGEN.ID_NO = CICGAGA_AGENT_AREA.ID_NO;
        CICSAGEN.AGENT_TP = "04";
        CICSAGEN.CI_NAME = CICGAGA_AGENT_AREA.CI_NM;
        CICSAGEN.PHONE = CICGAGA_AGENT_AREA.PHONE;
        S000_CALL_CIZSAGEN();
        if (pgmRtn) return;
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZMO);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_FUNC);
        if (DDCSOZM.INPUT_DATA.IZM_FUNC == '1') {
            DDCSZMO.AC_CNT = DDCSOZM.INPUT_DATA.IZM_AC_CNT;
            for (WS_CNT = 1; WS_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_CNT += 1) {
                B070_01_MOVE_TO_OZM();
                if (pgmRtn) return;
            }
        } else {
            DDCSZMO.AC_CNT = WS_LIST_CNT;
            for (WS_CNT = 1; WS_CNT <= DDCSOZM.INPUT_DATA.IZM_AC_CNT; WS_CNT += 1) {
                B070_03_MOVE_WS_LIST();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_FUNC);
        CEP.TRC(SCCGWA, "OUTPUT BV-LIST");
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_CNT);
        DDCSZMO.BV_CNT = DDCSOZM.INPUT_DATA.IZM_BV_CNT;
        CEP.TRC(SCCGWA, "BV-LIST2");
        CEP.TRC(SCCGWA, DDCSZMO.BV_CNT);
        for (WS_CNT = 1; WS_CNT <= DDCSOZM.INPUT_DATA.IZM_BV_CNT; WS_CNT += 1) {
            CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV);
            DDCSZMO.BV_LIST[WS_CNT-1].OPEN_BV = DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV;
            IBS.init(SCCGWA, DDRCCZM);
            DDRCCZM.KEY.OPEN_BV_CODE = DDCSOZM.OUTPUT_DATA.OZM_AC_LIST[1-1].OZM_AC_BV_CD;
            DDRCCZM.KEY.OPEN_BV = DDCSOZM.INPUT_DATA.IZM_BV_LIST[WS_CNT-1].IZM_OPEN_BV;
            DDRCCZM.KEY.REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
            CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
            CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
            CEP.TRC(SCCGWA, WS_REF_NO);
            T000_READ_DDTCCZM();
            if (pgmRtn) return;
            if (WS_DDTCCZM_REC == 'N') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_BV_NOT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                DDCSZMO.BV_LIST[WS_CNT-1].TS = DDRCCZM.TS;
            }
        }
        CEP.TRC(SCCGWA, K_FMT_CODE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCSZMO;
        SCCFMT.DATA_LEN = 5662;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DDCSZMO);
    }
    public void B070_01_MOVE_TO_OZM() throws IOException,SQLException,Exception {
        DDCSZMO.AC_LIST[WS_CNT-1].AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AC;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AC_SEQ;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_BV_CD = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AC_BV_CD;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_BV_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AC_BV_NO;
        DDCSZMO.AC_LIST[WS_CNT-1].CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_CCY;
        DDCSZMO.AC_LIST[WS_CNT-1].CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_CCY_TYPE;
        DDCSZMO.AC_LIST[WS_CNT-1].BAL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_CURR_BAL;
        DDCSZMO.AC_LIST[WS_CNT-1].AVAL_BAL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AVAL_BAL;
        DDCSZMO.AC_LIST[WS_CNT-1].HLD_AMT = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_OPEN_AMT;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_TYP = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_AC_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].VAL_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_VAL_DATE;
        DDCSZMO.AC_LIST[WS_CNT-1].EXP_DATE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_CNT-1].IZM_EXP_DATE;
        DDCSZMO.AC_LIST[WS_CNT-1].INT_TYP = WS_AC_LIST[WS_CNT-1].WS_INT_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = WS_AC_LIST[WS_CNT-1].WS_FBID_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, WS_AC_LIST[WS_CNT-1].WS_FBID_TYP);
        DDCSZMO.AC_LIST[WS_CNT-1].LOSS_TYP = '0';
        DDCSZMO.AC_LIST[WS_CNT-1].ZZ_TYP = '0';
        DDCSZMO.AC_LIST[WS_CNT-1].XX_TYP = '0';
        DDCSZMO.AC_LIST[WS_CNT-1].YY_TYP = '0';
        WS_HLD_AC = WS_AC_LIST[WS_CNT-1].WS_AC;
        IBS.init(SCCGWA, DCRHLD);
        T000_STARTBR_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DCTHLD_REC == 'Y') {
            if (DCRHLD.HLD_TYP == '1') {
                DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '1';
            } else {
                if (DCRHLD.HLD_TYP == '2') {
                    DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '2';
                }
            }
        } else {
            DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '0';
        }
        CEP.TRC(SCCGWA, DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP);
    }
    public void B070_03_MOVE_WS_LIST() throws IOException,SQLException,Exception {
        DDCSZMO.AC_LIST[WS_CNT-1].AC = WS_AC_LIST[WS_CNT-1].WS_AC;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_SEQ = WS_AC_LIST[WS_CNT-1].WS_AC_SEQ;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_BV_CD = WS_AC_LIST[WS_CNT-1].WS_AC_BV_CD;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_BV_NO = WS_AC_LIST[WS_CNT-1].WS_AC_BV_NO;
        DDCSZMO.AC_LIST[WS_CNT-1].CCY = WS_AC_LIST[WS_CNT-1].WS_CCY;
        DDCSZMO.AC_LIST[WS_CNT-1].CCY_TYPE = WS_AC_LIST[WS_CNT-1].WS_CCY_TYPE;
        DDCSZMO.AC_LIST[WS_CNT-1].BAL = WS_AC_LIST[WS_CNT-1].WS_CUR_BAL;
        DDCSZMO.AC_LIST[WS_CNT-1].AVAL_BAL = WS_AC_LIST[WS_CNT-1].WS_AVL_BAL;
        DDCSZMO.AC_LIST[WS_CNT-1].HLD_AMT = WS_AC_LIST[WS_CNT-1].WS_HLD_AMT;
        DDCSZMO.AC_LIST[WS_CNT-1].AC_TYP = WS_AC_LIST[WS_CNT-1].WS_AC_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].VAL_DATE = WS_AC_LIST[WS_CNT-1].WS_VAL_DATE;
        DDCSZMO.AC_LIST[WS_CNT-1].EXP_DATE = WS_AC_LIST[WS_CNT-1].WS_EXP_DATE;
        DDCSZMO.AC_LIST[WS_CNT-1].INT_TYP = WS_AC_LIST[WS_CNT-1].WS_INT_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = WS_AC_LIST[WS_CNT-1].WS_FBID_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].LOSS_TYP = WS_AC_LIST[WS_CNT-1].WS_LOSS_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].ZZ_TYP = WS_AC_LIST[WS_CNT-1].WS_ZZ_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].XX_TYP = WS_AC_LIST[WS_CNT-1].WS_XX_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].YY_TYP = WS_AC_LIST[WS_CNT-1].WS_YY_TYP;
        DDCSZMO.AC_LIST[WS_CNT-1].REF_NO = IBS.CLS2CPY(SCCGWA, WS_REF_NO);
        CEP.TRC(SCCGWA, DDCSZMO.AC_LIST[WS_CNT-1].BAL);
        CEP.TRC(SCCGWA, WS_AC_LIST[WS_CNT-1].WS_CUR_BAL);
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("0") 
            && TDCACE.PAGE_INF.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("0")) {
            DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = '0';
        }
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = '1';
        }
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = '2';
        }
        WS_HLD_AC = WS_AC_LIST[WS_CNT-1].WS_AC;
        IBS.init(SCCGWA, DCRHLD);
        T000_STARTBR_DCTHLD_FIRST();
        if (pgmRtn) return;
        if (WS_DCTHLD_REC == 'Y') {
            if (DCRHLD.HLD_TYP == '1') {
                DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '1';
            } else {
                if (DCRHLD.HLD_TYP == '2') {
                    DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '2';
                }
            }
        } else {
            DDCSZMO.AC_LIST[WS_CNT-1].HLD_TYP = '0';
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        if (DCCIMSTR.DATA.STS_WORD.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = '1';
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        if (DCCIMSTR.DATA.STS_WORD.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
            DDCSZMO.AC_LIST[WS_CNT-1].FBID_TYP = '2';
        }
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD.substring(2 - 1, 2 + 1 - 1));
        if (DCCIMSTR.DATA.STS_WORD == null) DCCIMSTR.DATA.STS_WORD = "";
        JIBS_tmp_int = DCCIMSTR.DATA.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DCCIMSTR.DATA.STS_WORD += " ";
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.STS_WORD.substring(5 - 1, 5 + 1 - 1));
    }
    public void B010_011_GET_DD_ACINFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
    }
    public void B200_BV_USE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BV-USE");
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.EC_IND = '1';
        BPCUBUSE.BV_CODE = DDRCCZM.KEY.OPEN_BV_CODE;
        BPCUBUSE.BEG_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.END_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.NUM = 1;
        BPCUBUSE.COUNT_MTH = '1';
        BPCUBUSE.VB_FLG = '0';
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        CEP.TRC(SCCGWA, BPCUBUSE.COUNT_MTH);
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P122";
        BPCPNHIS.INFO.TX_RMK = "存款证明�?�?";
        if (DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ != 0) {
            BPCPNHIS.INFO.TX_TOOL = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        } else {
            BPCPNHIS.INFO.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        }
        BPCPNHIS.INFO.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        BPCPNHIS.INFO.AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        BPCPNHIS.INFO.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        BPCPNHIS.INFO.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE, true);
    }
    public void T000_READ_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.READ(SCCGWA, DDRCCZM, DDTCCZM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCZM_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "CCZM-DUPKEY");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
            CEP.ERR(SCCGWA, WS_ERR_MSG, DDRCCZM.KEY.OPEN_BV);
        }
    }
    public void T00_WRITE_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        DDTZMAC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRZMAC, DDTZMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "DOUBLE KEY");
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTHLD_FIRST() throws IOException,SQLException,Exception {
        DCTHLD_RD = new DBParm();
        DCTHLD_RD.TableName = "DCTHLD";
        DCTHLD_RD.where = "AC = :WS_HLD_AC "
            + "AND HLD_STS = 'N'";
        DCTHLD_RD.fst = true;
        IBS.READ(SCCGWA, DCRHLD, this, DCTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DCTHLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DCTHLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTHLD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDRCCY.CUS_AC);
        CEP.TRC(SCCGWA, DDRCCY.CCY);
        CEP.TRC(SCCGWA, DDRCCY.CCY_TYPE);
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.where = "CUS_AC = :DDRCCY.CUS_AC "
            + "AND CCY = :DDRCCY.CCY "
            + "AND CCY_TYPE = :DDRCCY.CCY_TYPE";
        IBS.READ(SCCGWA, DDRCCY, this, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.PROD_CODE = "9710000002";
        BPCPOEWA.DATA.EVENT_CODE = "DR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[1-1].IZM_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TOTAL_AMT;
        BPCPOEWA.DATA.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void T000_CHECK_DCAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINF);
        DCCUCINF.CARD_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        S000_CALL_DCZUCINF();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCUCINF.CARD_STSW);
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCUCINF.CARD_STSW == null) DCCUCINF.CARD_STSW = "";
        JIBS_tmp_int = DCCUCINF.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCUCINF.CARD_STSW += " ";
        if (DCCUCINF.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_HAS_LOST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_CHECK_DDAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSCINM);
        DDCSCINM.INPUT_DATA.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDCSCINM.INPUT_DATA.FUNC = '2';
        S000_CALL_DDZSCINM();
        if (pgmRtn) return;
        if (DDCSCINM.OUTPUT_DATA.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD == null) DDCSCINM.OUTPUT_DATA.AC_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.AC_STS_WORD.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.AC_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.AC_STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD == null) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD == null) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD = "";
        JIBS_tmp_int = DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD += " ";
        if (DDCSCINM.OUTPUT_DATA.CCY_INFO[1-1].CCY_STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.CUS_AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        DDRCCY.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
        DDRCCY.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRCCY.STS_WORD);
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_IS_LONG_HOVER;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
        JIBS_tmp_int = DDRCCY.STS_WORD.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
        if (DDRCCY.STS_WORD.substring(11 - 1, 11 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '1') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_FREEZE_CNOT_TR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '2') {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(9 - 1, 9 + 1 - 1).equalsIgnoreCase("1")) {
                WS_DD_AC_HLD_FLG = 'Y';
            }
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            if (DDRCCY.STS_WORD.substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("1") 
                || DDRCCY.STS_WORD.substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("1")) {
                WS_DD_AC_FBD_FLG = 'Y';
            }
        }
    }
    public void T000_CHECK_TDAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCACE);
        TDCACE.PAGE_INF.AC_NO = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
        TDCACE.PAGE_INF.I_AC_SEQ = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC_SEQ;
        S000_CALL_TDZACE();
        if (pgmRtn) return;
        if (TDCACE.PAGE_INF.AC_STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_JSZM_AC_C;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_VCH_ORAL_LOSS_CNOT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.VCH_FORMAL_LOSS_CNOT_TR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.DATA[1-1].DAC_STSW == null) TDCACE.DATA[1-1].DAC_STSW = "";
        JIBS_tmp_int = TDCACE.DATA[1-1].DAC_STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].DAC_STSW += " ";
        if (TDCACE.DATA[1-1].DAC_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_FROZEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDCACE.PAGE_INF.STSW == null) TDCACE.PAGE_INF.STSW = "";
        JIBS_tmp_int = TDCACE.PAGE_INF.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.PAGE_INF.STSW += " ";
        if (TDCACE.PAGE_INF.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_PASSWD_LOCK_CNOT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '1') {
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ALREADY_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_ALREADY_AC_HLD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.BK_INTER_TEMP_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.AC_OFFICE_FORBID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (DDCSOZM.INPUT_DATA.IZM_BAL_TYPE == '2') {
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || TDCACE.DATA[1-1].ACO_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_TD_AC_HLD_FLG = 'Y';
            }
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(5 - 1, 5 + 1 - 1).equalsIgnoreCase("1")) {
                WS_TD_AC_PLG_FLG = 'Y';
            }
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW == null) TDCACE.DATA[1-1].ACO_STSW = "";
            JIBS_tmp_int = TDCACE.DATA[1-1].ACO_STSW.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) TDCACE.DATA[1-1].ACO_STSW += " ";
            if (TDCACE.DATA[1-1].ACO_STSW.substring(7 - 1, 7 + 1 - 1).equalsIgnoreCase("1") 
                || TDCACE.DATA[1-1].ACO_STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                WS_TD_AC_FBD_FLG = 'Y';
            }
        }
    }
    public void S000_CALL_DCZUHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HLD", DCCUHLD, true);
    }
    public void S000_CALL_DCZUHOLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-HOLD", DCCUHOLD, true);
    }
    public void S000_CALL_TDZACE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-AC-ENQ", TDCACE, true);
    }
    public void S000_CALL_DDZSCINM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-CI-NAME", DDCSCINM, true);
    }
    public void B800_GET_BAL_DATE_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_DATE);
        CEP.TRC(SCCGWA, DDCSOZM.INPUT_DATA.IZM_BAL_EXPD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (DDCSOZM.INPUT_DATA.IZM_BAL_EXPD < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.init(SCCGWA, BPCSOBAL);
            BPCSOBAL.INPUT.IN_DT = DDCSOZM.INPUT_DATA.IZM_BAL_EXPD;
            BPCSOBAL.INPUT.AC = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_AC;
            BPCSOBAL.INPUT.CCY = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY;
            BPCSOBAL.INPUT.CCY_TYPE = DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CCY_TYPE;
            S000_CALL_BPZSOBAL();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSOBAL.OUTPUT.CUR_BAL);
        DDCSOZM.INPUT_DATA.IZM_AC_LIST[WS_AC_CNT-1].IZM_CURR_BAL = BPCSOBAL.OUTPUT.CUR_BAL;
    }
    public void B020_WRITE_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "CCZM";
        BPCPOEWA.DATA.PROD_CODE = "XXX";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TOTAL_AMT;
        BPCPOEWA.DATA.CI_NO = DDCSOZM.INPUT_DATA.IZM_CI_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void S000_CALL_BPZSOBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-OBAL", BPCSOBAL, true);
        if (BPCSOBAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSOBAL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSIBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-INQ-CCY-BAL", DDCSIBAL, true);
    }
    public void S000_CALL_DCZIQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-VIA-BAL", DCCIQBAL, true);
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_DDZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-INQ-AC-INF", DCCPACTY, true);
        CEP.TRC(SCCGWA, DCCPACTY.RC.RC_CODE);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUABRW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-AC-BROWSER", DCCUABRW, true);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
    }
    public void S000_CALL_CIZSAGEN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SVR-CIZSAGEN", CICSAGEN);
        if (CICSAGEN.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICSAGEN.RC);
        }
    }
    public void S00_LINK_BPZEX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-EX", BPCEX, true);
        if (BPCEX.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCEX.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR, true);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
