package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPLDPD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_INQ_PRD_INFO = "BP-P-QUERY-PRDT-INFO";
    String CPN_P_INQ_RGND = "BP-P-INQ-RGND";
    String CPN_P_SET_PROD_CODE = "SC-SET-PROD-CODE";
    String CPN_P_SET_PRD_CTRL_IND = "SC-SET-PROD-CTRL-IND";
    String WS_PRDT_CODE = " ";
    int WS_CTRL_CNT = 0;
    BPZPLDPD_WS_RGN_NO WS_RGN_NO = new BPZPLDPD_WS_RGN_NO();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQRGD BPCPQRGD = new BPCPQRGD();
    SCCPROD SCCPROD = new SCCPROD();
    SCCGWA SCCGWA;
    BPCPLDPD BPCPLDPD;
    public void MP(SCCGWA SCCGWA, BPCPLDPD BPCPLDPD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPLDPD = BPCPLDPD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPLDPD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPLDPD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_TOP_PRDT_CODE();
        if (pgmRtn) return;
        B030_CHECK_PRDT_VALID();
        if (pgmRtn) return;
        B040_SEND_PRDT_INFO_TO_GWA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPLDPD.KIND != '1' 
            && BPCPLDPD.KIND != '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_KIND, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPLDPD.KIND == '1' 
            && BPCPLDPD.PRDT_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUST_INPUT, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPLDPD.KIND == '2' 
            && BPCPLDPD.ID_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ID_NO_MUST_INPUT, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_TOP_PRDT_CODE() throws IOException,SQLException,Exception {
        if (BPCPLDPD.KIND == '1') {
            IBS.init(SCCGWA, BPCPQPRD);
            BPCPQPRD.PRDT_CODE = BPCPLDPD.PRDT_CODE;
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_PRDT_CODE = BPCPQPRD.PRDT_CODE;
            WS_CTRL_CNT = BPCPQPRD.PRDT_COUNTER;
            CEP.TRC(SCCGWA, WS_PRDT_CODE);
            CEP.TRC(SCCGWA, WS_CTRL_CNT);
        } else if (BPCPLDPD.KIND == '2') {
            BPCPQPRD.PRDT_CODE = "DDCUA003";
            S000_CALL_BPZPQPRD();
            if (pgmRtn) return;
            WS_PRDT_CODE = BPCPQPRD.PRDT_CODE;
            WS_CTRL_CNT = BPCPQPRD.PRDT_COUNTER;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_KIND, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_CHECK_PRDT_VALID() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRD.EFF_DATE);
        CEP.TRC(SCCGWA, BPCPQPRD.EXP_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (BPCPQPRD.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE 
            || BPCPQPRD.EXP_DATE <= SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_OVERDUE_PRODUCT, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQPRD.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRDT_NOT_EFFECT, BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQPRD.SOLD_RGN.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPQRGD);
            BPCPQRGD.BNK = SCCGWA.COMM_AREA.TR_BANK;
            IBS.CPY2CLS(SCCGWA, BPCPQPRD.SOLD_RGN, WS_RGN_NO);
            BPCPQRGD.RGN_TYPE = WS_RGN_NO.WS_RGN_TYPE;
            BPCPQRGD.RGN_SEQ = WS_RGN_NO.WS_RGN_SEQ;
            BPCPQRGD.UNIT = "" + SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            JIBS_tmp_int = BPCPQRGD.UNIT.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCPQRGD.UNIT = "0" + BPCPQRGD.UNIT;
            CEP.TRC(SCCGWA, "TEST AAA");
            CEP.TRC(SCCGWA, BPCPQRGD);
            S000_CALL_BPZPQRGD();
            if (pgmRtn) return;
            if (BPCPQRGD.RC.RC_CODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_REGIOIN_RESTRICTED, BPCPLDPD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B040_SEND_PRDT_INFO_TO_GWA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCPROD);
        SCCPROD.CODE = WS_PRDT_CODE;
        S000_CALL_SCZPROD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PROD_CODE);
        if (BPCPQPRD.PRDT_COUNTER > 0) {
            S000_CALL_SCZPRDFN();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.PRDT_FUNC_CTRL_IND);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_PRD_INFO, BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQRGD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_RGND, BPCPQRGD);
    }
    public void S000_CALL_SCZPROD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_PROD_CODE, SCCPROD);
        if (SCCPROD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCPROD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPLDPD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCZPRDFN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_SET_PRD_CTRL_IND, null);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPLDPD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPLDPD = ");
            CEP.TRC(SCCGWA, BPCPLDPD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
