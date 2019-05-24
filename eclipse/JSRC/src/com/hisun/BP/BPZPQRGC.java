package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQRGC {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_MAINTAIN = "BP-PARM-MAINTAIN    ";
    BPZPQRGC_WS_RGNC_KEY WS_RGNC_KEY = new BPZPQRGC_WS_RGNC_KEY();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPRGCM BPRPRGCM = new BPRPRGCM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCPQRGC BPCPQRGC;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQRGC BPCPQRGC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQRGC = BPCPQRGC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQRGC return!");
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
        B020_GET_RGNC_INFO();
        if (pgmRtn) return;
        B030_OUTPUT_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQRGC.RGN_NO.RGN_TYPE);
        CEP.TRC(SCCGWA, BPCPQRGC.RGN_NO.RGN_SEQ);
        if (BPCPQRGC.RGN_NO.RGN_TYPE.trim().length() == 0 
            || BPCPQRGC.RGN_NO.RGN_SEQ == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCPQRGC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQRGC.BNK.equalsIgnoreCase("0")) {
            BPCPQRGC.BNK = SCCGWA.COMM_AREA.TR_BANK;
        }
        CEP.TRC(SCCGWA, BPCPQRGC.BNK);
    }
    public void B020_GET_RGNC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRGCM);
        IBS.init(SCCGWA, BPCPRMM);
        WS_RGNC_KEY.WS_RGNC_BK = BPCPQRGC.BNK;
        WS_RGNC_KEY.WS_RGNC_TYPE = BPCPQRGC.RGN_NO.RGN_TYPE;
        WS_RGNC_KEY.WS_RGNC_SEQ = BPCPQRGC.RGN_NO.RGN_SEQ;
        BPRPRGCM.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_RGNC_KEY);
        CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
        BPCPRMM.EFF_DT = BPCPQRGC.EFF_DT;
        BPCPRMM.FUNC = '3';
        CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
        BPRPRGCM.KEY.TYP = "RGNCD";
        BPCPRMM.DAT_PTR = BPRPRGCM;
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RGN_NO_NOTFND, BPCPQRGC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_OUTPUT_INFO() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, BPRPRGCM.KEY.CD, WS_RGNC_KEY);
        BPCPQRGC.BNK = WS_RGNC_KEY.WS_RGNC_BK;
        BPCPQRGC.RGN_NO.RGN_TYPE = WS_RGNC_KEY.WS_RGNC_TYPE;
        BPCPQRGC.RGN_NO.RGN_SEQ = WS_RGNC_KEY.WS_RGNC_SEQ;
        BPCPQRGC.ENG_NAME = BPRPRGCM.DESC;
        BPCPQRGC.CHN_NAME = BPRPRGCM.CDSC;
        BPCPQRGC.CALD_CD = BPRPRGCM.DATA_TXT.CALD_CD;
        BPCPQRGC.RMK = BPRPRGCM.DATA_TXT.RMK;
        BPCPQRGC.EFF_DT = BPRPRGCM.DATA_TXT.EFF_DT;
        BPCPQRGC.EXP_DT = BPRPRGCM.DATA_TXT.EXP_DT;
        BPCPQRGC.UPT_DT = BPRPRGCM.DATA_TXT.UPT_DT;
        BPCPQRGC.UPT_TLR = BPRPRGCM.DATA_TXT.UPT_TLR;
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_MAINTAIN, BPCPRMM);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQRGC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQRGC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQRGC = ");
            CEP.TRC(SCCGWA, BPCPQRGC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
