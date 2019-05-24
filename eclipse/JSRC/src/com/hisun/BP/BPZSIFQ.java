package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.FX.FXCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSIFQ {
    boolean pgmRtn = false;
    String CPN_R_TEXRM = "BP-R-EXRD-M       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WK_BR_HD = 999999;
    short WK_FX_EFF_MIN = 10;
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_READ_CNT = 0;
    int WS_BR = 0;
    String WS_CCY = " ";
    String WS_CORR_CCY = " ";
    String WS_BASE_CCY = " ";
    String WS_EXR_TYP = " ";
    String WS_FWD_TENOR = " ";
    int WS_DATE = 0;
    int WS_TIME_AREA = 0;
    BPZSIFQ_WS_TIME WS_TIME = new BPZSIFQ_WS_TIME();
    int WS_EX_HOUR = 0;
    int WS_EX_MINU = 0;
    int WS_EX_SECO = 0;
    int WS_TR_HOUR = 0;
    int WS_TR_MINU = 0;
    int WS_TR_SECO = 0;
    int WS_EX_SEC = 0;
    int WS_TR_SEC = 0;
    int WS_TM_DIF = 0;
    int WS_FX_EFF_DIF = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRFWDT BPRFWDT = new BPRFWDT();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCRTQPS BPCRTQPS = new BPCRTQPS();
    BPCRTQPH BPCRTQPH = new BPCRTQPH();
    BPCTTQPM BPCTTQPM = new BPCTTQPM();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPRTQP BPRTQP = new BPRTQP();
    BPRTQPH BPRTQPH = new BPRTQPH();
    BPREXRD BPREXRD = new BPREXRD();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCIFQ BPCIFQ;
    public void MP(SCCGWA SCCGWA, BPCIFQ BPCIFQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFQ = BPCIFQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSIFQ return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFQ.RC);
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EXR_TYPE);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.TENOR);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.BR);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CCY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CORR_CCY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.DT);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.TM);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_IFQ_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EX_CODE);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.UNT);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EFF_DT);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EFF_TM);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CS_SELL);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.FX_SELL);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.LOC_MID);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CCS_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CCS_SELL);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CFX_BUY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CFX_SELL);
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCIFQ.DATA.EXR_TYPE.trim().length() > 0) {
            WS_EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
            R000_CHECK_EXR_TYPE();
            if (pgmRtn) return;
            WS_BASE_CCY = BPREXRT.DAT_TXT.BASE_CCY;
        }
        CEP.TRC(SCCGWA, WS_BASE_CCY);
        BPCIFQ.DATA.TENOR = " ";
        CEP.TRC(SCCGWA, BPCIFQ.DATA.BR);
        if (BPCIFQ.DATA.BR != 0 
            && BPCIFQ.DATA.BR != WK_BR_HD) {
            WS_BR = BPCIFQ.DATA.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        } else {
            BPCIFQ.DATA.BR = WK_BR_HD;
        }
        if (BPCIFQ.DATA.CCY.equalsIgnoreCase(BPCIFQ.DATA.CORR_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_CCY;
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCIFQ.DATA.CCY.trim().length() > 0) {
            WS_CCY = BPCIFQ.DATA.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCIFQ.DATA.CORR_CCY.trim().length() > 0) {
            WS_CCY = BPCIFQ.DATA.CORR_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCIFQ.DATA.DT > 0) {
            if (BPCIFQ.DATA.DT > SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_ERR, BPCIFQ.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_DATE = BPCIFQ.DATA.DT;
                R000_CHECK_DATE();
                if (pgmRtn) return;
            }
        }
        if (BPCIFQ.DATA.TM > 0) {
            WS_TIME_AREA = BPCIFQ.DATA.TM;
            IBS.CPY2CLS(SCCGWA, WS_TIME_AREA+"", WS_TIME);
            if (WS_TIME.WS_HOUR > 23 
                || WS_TIME.WS_MINU > 60 
                || WS_TIME.WS_SECD > 60) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TIME_ERR, BPCIFQ.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_IFQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = BPCIFQ.DATA.CCY;
        BPCOIEC.CCY2 = BPCIFQ.DATA.CORR_CCY;
        BPCOIEC.EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
        S000_CALL_BPZSIEC();
        if (pgmRtn) return;
        BPCIFQ.DATA.EX_CODE = BPCOIEC.EXR_CODE;
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EX_CODE);
        IBS.init(SCCGWA, BPCTEXRM);
        IBS.init(SCCGWA, BPREXRD);
        if (!BPCIFQ.DATA.CCY.equalsIgnoreCase(WS_BASE_CCY)) {
            BPREXRD.KEY.CCY = BPCIFQ.DATA.CCY;
        } else {
            BPREXRD.KEY.CCY = BPCIFQ.DATA.CORR_CCY;
        }
        BPREXRD.KEY.EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
        BPREXRD.KEY.FWD_TENOR = BPCIFQ.DATA.TENOR;
        BPCTEXRM.INFO.FUNC = 'Q';
        S000_CALL_BPZTEXRM();
        if (pgmRtn) return;
        BPCIFQ.DATA.UNT = BPREXRD.UNT;
        CEP.TRC(SCCGWA, BPCIFQ.DATA.TM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        if ((BPCIFQ.DATA.DT == SCCGWA.COMM_AREA.AC_DATE 
            && BPCIFQ.DATA.TM >= SCCGWA.COMM_AREA.TR_TIME) 
            || (BPCIFQ.DATA.DT == 0 
            && BPCIFQ.DATA.TM == 0)) {
            B250_INQ_CURRENT_RATE();
            if (pgmRtn) return;
        } else {
            B260_INQ_HISTROY_RATE();
            if (pgmRtn) return;
        }
    }
    public void B250_INQ_CURRENT_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPS);
        IBS.init(SCCGWA, BPRTQP);
        BPRTQP.KEY.EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
        BPRTQP.KEY.BR = BPCIFQ.DATA.BR;
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQP.KEY.CCY = BPCIFQ.DATA.EX_CODE.substring(0, 3);
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQP.KEY.CORR_CCY = BPCIFQ.DATA.EX_CODE.substring(4 - 1, 4 + 3 - 1);
        CEP.TRC(SCCGWA, BPRTQP.KEY.EXR_TYP);
        CEP.TRC(SCCGWA, BPRTQP.KEY.FWD_TENOR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.BR);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CCY);
        CEP.TRC(SCCGWA, BPRTQP.KEY.CORR_CCY);
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND BR = :BPRTQP.KEY.BR "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCIFQ.DATA.EFF_DT = BPRTQP.KEY.EFF_DT;
            BPCIFQ.DATA.EFF_TM = BPRTQP.KEY.EFF_TM;
            BPCIFQ.DATA.CS_BUY = BPRTQP.CS_BUY;
            BPCIFQ.DATA.CS_SELL = BPRTQP.CS_SELL;
            BPCIFQ.DATA.FX_BUY = BPRTQP.FX_BUY;
            BPCIFQ.DATA.FX_SELL = BPRTQP.FX_SELL;
            BPCIFQ.DATA.CCS_BUY = BPRTQP.CCS_BUY;
            BPCIFQ.DATA.CCS_SELL = BPRTQP.CCS_SELL;
            BPCIFQ.DATA.CFX_BUY = BPRTQP.CFX_BUY;
            BPCIFQ.DATA.CFX_SELL = BPRTQP.CFX_SELL;
            if (BPRTQP.LOC_MID == 0) {
                BPCIFQ.DATA.LOC_MID = ( BPCIFQ.DATA.FX_BUY + BPCIFQ.DATA.FX_SELL ) / 2;
            } else {
                BPCIFQ.DATA.LOC_MID = BPRTQP.LOC_MID;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SYS_RATE_NOT_EXIST, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B250_10_CHK_TM() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = "" + BPCIFQ.DATA.EFF_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_EX_HOUR = 0;
        else WS_EX_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + BPCIFQ.DATA.EFF_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_EX_MINU = 0;
        else WS_EX_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + BPCIFQ.DATA.EFF_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_EX_SECO = 0;
        else WS_EX_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 2).trim().length() == 0) WS_TR_HOUR = 0;
        else WS_TR_HOUR = Integer.parseInt(JIBS_tmp_str[0].substring(0, 2));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1).trim().length() == 0) WS_TR_MINU = 0;
        else WS_TR_MINU = Integer.parseInt(JIBS_tmp_str[0].substring(3 - 1, 3 + 2 - 1));
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<6-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) WS_TR_SECO = 0;
        else WS_TR_SECO = Integer.parseInt(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        CEP.TRC(SCCGWA, WS_EX_HOUR);
        CEP.TRC(SCCGWA, WS_EX_MINU);
        CEP.TRC(SCCGWA, WS_EX_SECO);
        CEP.TRC(SCCGWA, WS_TR_HOUR);
        CEP.TRC(SCCGWA, WS_TR_MINU);
        CEP.TRC(SCCGWA, WS_TR_SECO);
