package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIACCT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_MSG1 = "DC2412";
    String K_MSG2 = "DC2413";
    String K_MSG3 = "DC2414";
    String K_MSG4 = "DC2415";
    String WS_ERR_MSG = " ";
    DCZIACCT_WS_PARM WS_PARM = new DCZIACCT_WS_PARM();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    DCCIACCT DCCIACCT;
    public void MP(SCCGWA SCCGWA, DCCIACCT DCCIACCT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIACCT = DCCIACCT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIACCT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        DCCIACCT.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        DCCIACCT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT_PROC();
        if (pgmRtn) return;
        B020_INQ_AC_CT_PARM();
        if (pgmRtn) return;
        B030_RETURN_CT_MSG();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (DCCIACCT.DATA.AC_PURP.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_AC_PURP_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCIACCT.DATA.OPT == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_OPT_TYPE_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_AC_CT_PARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_PARM);
        IBS.init(SCCGWA, BPCPRMR);
        WS_PARM.WS_PARM_KEY.WS_PARM_TYP = "PDD16";
        WS_PARM.WS_PARM_KEY.WS_PARM_CD = DCCIACCT.DATA.AC_PURP;
        BPCPRMR.DAT_PTR = WS_PARM;
        CEP.TRC(SCCGWA, WS_PARM.WS_PARM_KEY.WS_PARM_TYP);
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_PARM.WS_PARM_DATA_TXT);
    }
    public void B030_RETURN_CT_MSG() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
        } else {
            if (DCCIACCT.DATA.OPT == 'H') {
                if (WS_PARM.WS_PARM_DATA_TXT.WS_PARM_HLD == 'E') {
                    IBS.CPY2CLS(SCCGWA, K_MSG1, DCCIACCT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (WS_PARM.WS_PARM_DATA_TXT.WS_PARM_FDR == 'E') {
                        IBS.CPY2CLS(SCCGWA, K_MSG2, DCCIACCT.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else if (DCCIACCT.DATA.OPT == 'D') {
                if (WS_PARM.WS_PARM_DATA_TXT.WS_PARM_FDR == 'E') {
                    IBS.CPY2CLS(SCCGWA, K_MSG3, DCCIACCT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else if (DCCIACCT.DATA.OPT == 'I') {
                if (WS_PARM.WS_PARM_DATA_TXT.WS_PARM_HLD == 'E' 
                    && WS_PARM.WS_PARM_DATA_TXT.WS_PARM_FDR == 'E') {
                    IBS.CPY2CLS(SCCGWA, K_MSG4, DCCIACCT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (WS_PARM.WS_PARM_DATA_TXT.WS_PARM_HLD != 'E' 
                    && WS_PARM.WS_PARM_DATA_TXT.WS_PARM_FDR == 'E') {
                    IBS.CPY2CLS(SCCGWA, K_MSG2, DCCIACCT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            } else {
                IBS.init(SCCGWA, SCCEXCP);
                SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DCCIACCT.DATA.OPT + ")";
                CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
            }
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        CEP.TRC(SCCGWA, BPCPRMR.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
        } else {
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIACCT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCIACCT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIACCT=");
            CEP.TRC(SCCGWA, DCCIACCT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
