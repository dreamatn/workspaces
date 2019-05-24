package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUTCVC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_P_CHK_CBOX = "BP-P-CHK-CBOX       ";
    String CPN_P_DEL_CBOX = "BP-P-DEL-CBOX       ";
    String CPN_F_TLR_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_CHK_VOUCHER = "BP-F-TLR-BV-QUERY   ";
    String CPN_P_DEL_VOUCHER = "BP-U-DEL-TBV        ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPCBOX BPCPCBOX = new BPCPCBOX();
    BPCPDBOX BPCPDBOX = new BPCPDBOX();
    BPCFBVTL BPCFBVTL = new BPCFBVTL();
    BPCUDTBV BPCUDTBV = new BPCUDTBV();
    SCCGWA SCCGWA;
    BPCUTCVC BPCUTCVC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCUTCVC BPCUTCVC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUTCVC = BPCUTCVC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUTCVC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCUTCVC.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCUTCVC.TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCUTCVC.CASH_BV_FLG == '0') {
            IBS.init(SCCGWA, BPCPCBOX);
            if (BPCUTCVC.DEL_FLG == 'Y') {
                BPCPCBOX.FUNC = 'R';
            } else {
                BPCPCBOX.FUNC = 'A';
            }
            BPCPCBOX.TLR = BPCUTCVC.TLR;
            BPCPCBOX.VB_FLG = '0';
            S000_CALL_BPZPCBOX();
            if (pgmRtn) return;
            if (BPCPCBOX.CHK_RLT_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_RLTD_BOX, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCBOX.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_BAL_FLG)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BAL_FLG, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (BPCUTCVC.CASH_BV_FLG == '1') {
            IBS.init(SCCGWA, BPCPCBOX);
            if (BPCUTCVC.DEL_FLG == 'Y') {
                BPCPCBOX.FUNC = 'R';
            } else {
                BPCPCBOX.FUNC = 'A';
            }
            BPCPCBOX.BR = BPCFTLRQ.INFO.NEW_BR;
            BPCPCBOX.TLR = BPCUTCVC.TLR;
            BPCPCBOX.VB_FLG = '1';
            S000_CALL_BPZPCBOX();
            if (pgmRtn) return;
            if (BPCPCBOX.CHK_RLT_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_RLTD_BOX, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (BPCUTCVC.CASH_BV_FLG == '2') {
            IBS.init(SCCGWA, BPCFBVTL);
            if (BPCUTCVC.DEL_FLG == 'Y') {
                BPCFBVTL.DELETE_FLG = 'Y';
            }
            BPCFBVTL.TLR = BPCUTCVC.TLR;
            S000_CALL_BPZFBVTL();
            if (pgmRtn) return;
            if (BPCFBVTL.BV_BBAL_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_BOX_BAL_MUST_ZERO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BL_BBAL_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BL_BOX_BAL_MUST_ZERO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BV_BCHK_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_NO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BL_BCHK_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BL_BCHK_FLG_NO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BV_ONWAY_FLG == 'Y' 
                || BPCFBVTL.BL_ONWAY_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_OR_BL_HAVE_ONWAY, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (BPCUTCVC.CASH_BV_FLG == '3') {
            IBS.init(SCCGWA, BPCFBVTL);
            if (BPCUTCVC.DEL_FLG == 'Y') {
                BPCFBVTL.DELETE_FLG = 'Y';
            }
            BPCFBVTL.TLR = BPCUTCVC.TLR;
            S000_CALL_BPZFBVTL();
            if (pgmRtn) return;
            if (BPCFBVTL.BV_VBAL_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_LIB_BAL_MUST_ZERO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BL_VBAL_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BL_LIB_BAL_MUST_ZERO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BV_VCHK_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_VCHK_FLG_NO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BL_VCHK_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BL_VCHK_FLG_NO, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (BPCFBVTL.BV_ONWAY_FLG == 'Y' 
                || BPCFBVTL.BL_ONWAY_FLG == 'Y') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BV_OR_BL_HAVE_ONWAY, BPCUTCVC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_CBOX, BPCPCBOX);
        if (BPCPCBOX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPCBOX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPDBOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_DEL_CBOX, BPCPDBOX);
        if (BPCPDBOX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPDBOX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVTL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_CHK_VOUCHER, BPCFBVTL);
        if (BPCFBVTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFBVTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUDTBV() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_DEL_VOUCHER, BPCUDTBV);
        if (BPCUDTBV.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUDTBV.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTCVC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
