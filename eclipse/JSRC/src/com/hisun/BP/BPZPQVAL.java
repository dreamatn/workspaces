package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQVAL {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPZPQVAL_WS_CDTL_KEY WS_CDTL_KEY = new BPZPQVAL_WS_CDTL_KEY();
    char WS_TBL_CDTL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPCDTL BPRPCDTL = new BPRPCDTL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    BPCPQVAL BPCPQVAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQVAL BPCPQVAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQVAL = BPCPQVAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQVAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQVAL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CDTL_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCPQVAL.CCY.trim().length() == 0 
            || BPCPQVAL.PAR_VAL == 0 
            || BPCPQVAL.M_FLG == ' ') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQVAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CDTL_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPCDTL);
        IBS.init(SCCGWA, BPCPRMR);
        WS_CDTL_KEY.WS_CDTL_CCY = BPCPQVAL.CCY;
        WS_CDTL_KEY.WS_CDTL_PAR_VAL = BPCPQVAL.PAR_VAL;
        WS_CDTL_KEY.WS_CDTL_M_FLG = BPCPQVAL.M_FLG;
        CEP.TRC(SCCGWA, WS_CDTL_KEY.WS_CDTL_CCY);
        CEP.TRC(SCCGWA, WS_CDTL_KEY.WS_CDTL_PAR_VAL);
        CEP.TRC(SCCGWA, WS_CDTL_KEY.WS_CDTL_M_FLG);
        BPRPCDTL.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_CDTL_KEY);
        BPRPCDTL.KEY.TYP = "PVAL ";
        BPCPRMR.DAT_PTR = BPRPCDTL;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRPCDTL.DATA_TXT.R_FLG);
        CEP.TRC(SCCGWA, BPRPCDTL.KEY.CD);
        CEP.TRC(SCCGWA, BPCPQVAL.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            || BPRPCDTL.DATA_TXT.R_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PVAL_NOT_AVAILABLE, BPCPQVAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQVAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQVAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQVAL = ");
            CEP.TRC(SCCGWA, BPCPQVAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
