package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIFT {
    int JIBS_tmp_int;
    DBParm BPTTQP_RD;
    DBParm BPTTQPH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TQP = "BP-R-TQP-M        ";
    String CPN_R_TQPH = "BP-R-TQPH-B       ";
    String CPN_R_TQPS = "BP-R-TQP-B        ";
    String CPN_R_TEXRM = "BP-R-EXRD-M       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_PARM_EXR_TYP = "FXRT";
    String K_PUB_CODE_FWDT = "FWDT";
    int K_MAX_DATE = 99991231;
    int K_MAX_TIME = 235959;
    int K_BR_HD = 999999;
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
    BPZSIFT_WS_TIME WS_TIME = new BPZSIFT_WS_TIME();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
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
        CEP.TRC(SCCGWA, "BPZSIFT return!");
        Z_RET();
        if (pgmRtn) return;
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
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EXR_TYPE);
        if (BPCIFQ.DATA.EXR_TYPE.trim().length() > 0) {
            WS_EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
            R000_CHECK_EXR_TYPE();
            if (pgmRtn) return;
            WS_BASE_CCY = BPREXRT.DAT_TXT.BASE_CCY;
        }
        CEP.TRC(SCCGWA, WS_BASE_CCY);
        BPCIFQ.DATA.TENOR = " ";
        CEP.TRC(SCCGWA, BPCIFQ.DATA.BR);
        if (BPCIFQ.DATA.BR != 0) {
            WS_BR = BPCIFQ.DATA.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        } else {
            BPCIFQ.DATA.BR = K_BR_HD;
        }
        if (BPCIFQ.DATA.CCY.equalsIgnoreCase(BPCIFQ.DATA.CORR_CCY)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_SAME_CCY;
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CCY);
        if (BPCIFQ.DATA.CCY.trim().length() > 0) {
            WS_CCY = BPCIFQ.DATA.CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CORR_CCY);
        if (BPCIFQ.DATA.CORR_CCY.trim().length() > 0) {
            WS_CCY = BPCIFQ.DATA.CORR_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MUST_IN_CCY, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCIFQ.DATA.DT);
        if (BPCIFQ.DATA.DT == 0) {
            BPCIFQ.DATA.DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
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
        CEP.TRC(SCCGWA, BPCIFQ.DATA.TM);
        if (BPCIFQ.DATA.TM == 0) {
            BPCIFQ.DATA.TM = SCCGWA.COMM_AREA.TR_TIME;
        } else {
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
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CCY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.CORR_CCY);
        CEP.TRC(SCCGWA, BPCIFQ.DATA.EXR_TYPE);
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = BPCIFQ.DATA.CCY;
        BPCOIEC.CCY2 = BPCIFQ.DATA.CORR_CCY;
        BPCOIEC.EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
        S000_CALL_BPZSIEC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOIEC.EXR_CODE);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_TIME);
        if (BPCIFQ.DATA.DT == SCCGWA.COMM_AREA.AC_DATE 
            && (BPCIFQ.DATA.TM == SCCGWA.COMM_AREA.TR_TIME 
            || BPCIFQ.DATA.TM > SCCGWA.COMM_AREA.TR_TIME)) {
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
        BPRTQP.KEY.FWD_TENOR = BPCIFQ.DATA.TENOR;
        BPRTQP.KEY.BR = BPCIFQ.DATA.BR;
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQP.KEY.CCY = BPCIFQ.DATA.EX_CODE.substring(0, 3);
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQP.KEY.CORR_CCY = BPCIFQ.DATA.EX_CODE.substring(4 - 1, 4 + 3 - 1);
        BPRTQP.KEY.EFF_DT = BPCIFQ.DATA.DT;
        BPCRTQPS.INFO.POINTER = BPRTQP;
        BPCRTQPS.INFO.LEN = 401;
        null.fst = true;
        BPTTQP_RD = new DBParm();
        BPTTQP_RD.TableName = "BPTTQP";
        BPTTQP_RD.where = "EXR_TYP = :BPRTQP.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQP.KEY.FWD_TENOR "
            + "AND BR = :BPRTQP.KEY.BR "
            + "AND CCY = :BPRTQP.KEY.CCY "
            + "AND CORR_CCY = :BPRTQP.KEY.CORR_CCY "
            + "AND EFF_DT = :BPRTQP.KEY.EFF_DT";
        BPTTQP_RD.order = "EFF_TM DESC";
        IBS.READ(SCCGWA, BPRTQP, this, BPTTQP_RD);
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
            if (BPRTQP.LOC_MID == 0) {
                BPCIFQ.DATA.LOC_MID = ( BPCIFQ.DATA.FX_BUY + BPCIFQ.DATA.FX_SELL ) / 2;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SYS_RATE_NOT_EXIST, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B260_INQ_HISTROY_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTQPH);
        IBS.init(SCCGWA, BPRTQPH);
        BPRTQPH.KEY.EXR_TYP = BPCIFQ.DATA.EXR_TYPE;
        BPRTQPH.KEY.FWD_TENOR = BPCIFQ.DATA.TENOR;
        BPRTQPH.KEY.BR = BPCIFQ.DATA.BR;
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQPH.KEY.CCY = BPCIFQ.DATA.EX_CODE.substring(0, 3);
        if (BPCIFQ.DATA.EX_CODE == null) BPCIFQ.DATA.EX_CODE = "";
        JIBS_tmp_int = BPCIFQ.DATA.EX_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCIFQ.DATA.EX_CODE += " ";
        BPRTQPH.KEY.CORR_CCY = BPCIFQ.DATA.EX_CODE.substring(4 - 1, 4 + 3 - 1);
        BPRTQPH.EFF_DT = BPCIFQ.DATA.DT;
        BPRTQPH.EFF_TM = BPCIFQ.DATA.TM;
        BPCRTQPH.INFO.POINTER = BPRTQPH;
        BPCRTQPH.INFO.LEN = 412;
        null.fst = true;
        BPTTQPH_RD = new DBParm();
        BPTTQPH_RD.TableName = "BPTTQPH";
        BPTTQPH_RD.where = "EXR_TYP = :BPRTQPH.KEY.EXR_TYP "
            + "AND FWD_TENOR = :BPRTQPH.KEY.FWD_TENOR "
            + "AND BR = :BPRTQPH.KEY.BR "
            + "AND CCY = :BPRTQPH.KEY.CCY "
            + "AND CORR_CCY = :BPRTQPH.KEY.CORR_CCY "
            + "AND EFF_DT = :BPRTQPH.EFF_DT "
            + "AND ( EFF_TM < :BPRTQPH.EFF_TM "
            + "OR EFF_TM = :BPRTQPH.EFF_TM )";
        BPTTQPH_RD.order = "EXR_TYP, FWD_TENOR, BR, CCY, CORR_CCY, EFF_DT DESC, EFF_TM DESC";
        IBS.READ(SCCGWA, BPRTQPH, this, BPTTQPH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCIFQ.DATA.EFF_DT = BPRTQPH.EFF_DT;
            BPCIFQ.DATA.EFF_TM = BPRTQPH.EFF_TM;
            BPCIFQ.DATA.CS_BUY = BPRTQPH.CS_BUY;
            BPCIFQ.DATA.CS_SELL = BPRTQPH.CS_SELL;
            BPCIFQ.DATA.FX_BUY = BPRTQPH.FX_BUY;
            BPCIFQ.DATA.FX_SELL = BPRTQPH.FX_SELL;
            BPCIFQ.DATA.CCS_BUY = BPRTQPH.CCS_BUY;
            BPCIFQ.DATA.CCS_SELL = BPRTQPH.CCS_SELL;
            BPCIFQ.DATA.CFX_BUY = BPRTQPH.CFX_BUY;
            if (BPRTQP.LOC_MID == 0) {
                BPCIFQ.DATA.LOC_MID = ( BPCIFQ.DATA.FX_BUY + BPCIFQ.DATA.FX_SELL ) / 2;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SYS_RATE_NOT_EXIST, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R000_CHECK_BRANCH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_EXR_TYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = WS_EXR_TYP;
        BPCPRMR.DAT_PTR = BPREXRT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            IBS.CPY2CLS(SCCGWA, SCCCKDT.RC+"", BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTEXRM() throws IOException,SQLException,Exception {
        BPCTEXRM.POINTER = BPREXRD;
        BPCTEXRM.LEN = 259;
        IBS.CALLCPN(SCCGWA, CPN_R_TEXRM, BPCTEXRM);
        if (BPCTEXRM.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_RATE_UNDEFINE, BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        CEP.TRC(SCCGWA, "BPZPRMR END");
    }
    public void S000_CALL_BPZPQPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_CODE, BPCOQPCD);
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        CEP.TRC(SCCGWA, BPCOIEC.RC.RC_CODE);
        if (BPCOIEC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOIEC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCIFQ.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
