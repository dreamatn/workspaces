package com.hisun.VT;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOFBAS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPDM;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPQRGC;
import com.hisun.BP.BPCQCCY;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;

public class VTZSPODR {
    boolean pgmRtn = false;
    String CPN_P_INQ_ORG_LOW = "BP-P-INQ-ORG-LOW";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY";
    String WS_ERR_MSG = " ";
    short WS_CNT = 0;
    short WS_LEN = 0;
    short WS_LENGH = 0;
    short WS_LENGH1 = 0;
    short WS_LENGH2 = 0;
    short WS_LENGH3 = 0;
    VTZSPODR_WS_BR_RANGE WS_BR_RANGE = new VTZSPODR_WS_BR_RANGE();
    VTZSPODR_WS_OUTPUT WS_OUTPUT = new VTZSPODR_WS_OUTPUT();
    VTZSPODR_WS_BROWSE_OUTPUT WS_BROWSE_OUTPUT = new VTZSPODR_WS_BROWSE_OUTPUT();
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    VTCMSG_ERROR_MSG VTCMSG_ERROR_MSG = new VTCMSG_ERROR_MSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    VTRPODR VTROPODR = new VTRPODR();
    VTRPODR VTRNPODR = new VTRPODR();
    SCCMPAG SCCMPAG = new SCCMPAG();
    VTRPODR VTRPODR = new VTRPODR();
    VTCRPODR VTCRPODR = new VTCRPODR();
    VTRVTCD VTRVTCD = new VTRVTCD();
    VTCRVTCD VTCRVTCD = new VTCRVTCD();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    BPCPQRGC BPCPQRGC = new BPCPQRGC();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOFBAS BPCOUBAS = new BPCOFBAS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    VTCSPODR VTCSPODR;
    public void MP(SCCGWA SCCGWA, VTCSPODR VTCSPODR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.VTCSPODR = VTCSPODR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "VTZSPODR return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (VTCSPODR.FUNC == 'B') {
            B020_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPODR.FUNC == 'A') {
            B040_ADD_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPODR.FUNC == 'Q') {
            B030_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (VTCSPODR.FUNC == 'M') {
            B050_MODIFY_RECORD();
            if (pgmRtn) return;
        } else if (VTCSPODR.FUNC == 'D') {
            B070_DELETE_RECORD();
            if (pgmRtn) return;
        } else if (VTCSPODR.FUNC == 'R') {
            B060_REACTIVE_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_FUNC_CODE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (VTCSPODR.FUNC != 'B') {
            R000_PODR_INFO_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (VTCSPODR.FUNC == 'A') {
            IBS.init(SCCGWA, VTCRPODR);
            IBS.init(SCCGWA, VTRPODR);
            VTRPODR.KEY.PROD_CD = VTCSPODR.PROD_CD;
            VTRPODR.KEY.BR = VTCSPODR.BR;
            VTRPODR.KEY.CCY = VTCSPODR.CCY;
            VTRPODR.KEY.CNTR_TYPE = VTCSPODR.CNTR_TYPE;
            VTRPODR.KEY.OTH = VTCSPODR.OTH;
            CEP.TRC(SCCGWA, VTRPODR.KEY.PROD_CD);
            CEP.TRC(SCCGWA, VTRPODR.KEY.BR);
            CEP.TRC(SCCGWA, VTRPODR.KEY.CCY);
            CEP.TRC(SCCGWA, VTRPODR.KEY.CNTR_TYPE);
            if (VTRPODR.KEY.OTH == null) VTRPODR.KEY.OTH = "";
            JIBS_tmp_int = VTRPODR.KEY.OTH.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) VTRPODR.KEY.OTH += " ";
            CEP.TRC(SCCGWA, VTRPODR.KEY.OTH.substring(21 - 1, 21 + 1 - 1));
            VTCRPODR.FUNC = 'Q';
            VTCRPODR.POINTER = VTRPODR;
            VTCRPODR.REC_LEN = 225;
            CEP.TRC(SCCGWA, VTCRPODR.REC_LEN);
            S000_CALL_VTZRPODR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, VTCRPODR.REC_LEN);
            if (VTCRPODR.RETURN_INFO == 'F') {
                WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_PODR_KEY_FOUND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (VTCSPODR.CODE.trim().length() > 0) {
                IBS.init(SCCGWA, VTRVTCD);
                IBS.init(SCCGWA, VTCRVTCD);
                CEP.TRC(SCCGWA, VTCSPODR.CODE);
                VTRVTCD.KEY.CODE = VTCSPODR.CODE;
                VTCRVTCD.FUNC = 'Q';
                CEP.TRC(SCCGWA, VTRVTCD.KEY.CODE);
                VTCRVTCD.POINTER = VTRVTCD;
                VTCRVTCD.REC_LEN = 206;
                S000_CALL_VTZRVTCD();
                if (pgmRtn) return;
                if (VTCRVTCD.RETURN_INFO == 'N') {
                    WS_ERR_MSG = VTCMSG_ERROR_MSG.VT_TAX_CODE_NOT_FOUND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (VTCSPODR.CNTR_TYPE.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CNTR_TYPE_MUST_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPQPDM);
                BPCPQPDM.PRDT_MODEL = VTCSPODR.CNTR_TYPE;
                S000_CALL_BPZPQPDM();
                if (pgmRtn) return;
            }
            if (!VTCSPODR.CCY.equalsIgnoreCase("999")) {
                IBS.init(SCCGWA, BPCQCCY);
                BPCQCCY.DATA.CCY = VTCSPODR.CCY;
                S000_CALL_BPZQCCY();
                if (pgmRtn) return;
            }
            if (VTCSPODR.BR != 99999999) {
                IBS.init(SCCGWA, BPCPQORG);
                BPCPQORG.BR = VTCSPODR.BR;
                S000_CALL_BPZPQORG();
                if (pgmRtn) return;
            }
        }
        if (VTCSPODR.FUNC == 'A' 
            || VTCSPODR.FUNC == 'M') {
            if (VTCSPODR.BR_RGN.trim().length() > 0) {
                IBS.init(SCCGWA, BPCPQRGC);
                IBS.CPY2CLS(SCCGWA, VTCSPODR.BR_RGN, WS_BR_RANGE);
                BPCPQRGC.RGN_NO.RGN_TYPE = WS_BR_RANGE.WS_BR_TYPE;
                BPCPQRGC.RGN_NO.RGN_SEQ = WS_BR_RANGE.WS_BR_SEQ;
                BPCPQRGC.BNK = SCCGWA.COMM_AREA.TR_BANK;
                S000_CALL_BPZPQRGC();
                if (pgmRtn) return;
            }
        }
        if (VTCSPODR.FUNC == 'A' 
            || VTCSPODR.FUNC == 'M') {
            IBS.init(SCCGWA, VTRVTCD);
            IBS.init(SCCGWA, VTCRVTCD);
            VTRVTCD.KEY.CODE = VTCSPODR.CODE;
            VTCRVTCD.FUNC = 'Q';
            VTCRVTCD.POINTER = VTRVTCD;
            VTCRVTCD.REC_LEN = 206;
            S000_CALL_VTZRVTCD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, VTRVTCD.RT);
