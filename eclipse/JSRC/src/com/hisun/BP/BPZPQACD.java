package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQACD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_PANCD = "PANCD";
    String K_PGM_NAME = "BPZPQACD";
    String K_R_MBRW_PARM = "BP-R-MBRW-PARM";
    String WS_ERR_MSG = " ";
    BPZPQACD_WS_PANCD_DATA WS_PANCD_DATA = new BPZPQACD_WS_PANCD_DATA();
    char WS_STOP_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRPARM BPRPARM = new BPRPARM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    BPCOQACD BPCOQACD;
    public void MP(SCCGWA SCCGWA, BPCOQACD BPCOQACD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOQACD = BPCOQACD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQACD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_STOP_FLG = ' ';
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCOQACD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_PANCD_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCOQACD.TXN_CODE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXN_CD_EMPTY, BPCOQACD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_PANCD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_PANCD;
        BPRPARM.KEY.CODE = BPCOQACD.TXN_CODE;
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        WS_STOP_FLG = 'N';
        BPCOQACD.CNT = 0;
        while (WS_STOP_FLG != 'Y') {
            IBS.init(SCCGWA, BPRPARM);
            IBS.init(SCCGWA, BPCRMBPM);
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            if (WS_STOP_FLG != 'Y') {
                if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                if (BPCOQACD.TXN_CODE.equalsIgnoreCase(BPRPARM.KEY.CODE.substring(0, 7))) {
                    B020_01_GET_PANCD_INFO();
                    if (pgmRtn) return;
                } else {
                    WS_STOP_FLG = 'Y';
                }
            }
        }
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B020_01_GET_PANCD_INFO() throws IOException,SQLException,Exception {
        BPCOQACD.CNT = BPCOQACD.CNT + 1;
        IBS.CPY2CLS(SCCGWA, BPRPARM.BLOB_VAL, WS_PANCD_DATA);
        BPCOQACD.DATA[BPCOQACD.CNT-1].DESC_ENG = WS_PANCD_DATA.WS_FLD_DESCE;
        BPCOQACD.DATA[BPCOQACD.CNT-1].DESC_CHS = WS_PANCD_DATA.WS_FLD_DESC;
        BPCOQACD.DATA[BPCOQACD.CNT-1].TYPE = WS_PANCD_DATA.WS_FLD_TYPE;
        BPCOQACD.DATA[BPCOQACD.CNT-1].LEN = WS_PANCD_DATA.WS_FLD_LEN;
        BPCOQACD.DATA[BPCOQACD.CNT-1].MAX_LEN = WS_PANCD_DATA.WS_FLD_MAX_LEN;
        BPCOQACD.DATA[BPCOQACD.CNT-1].DEC_LEN = WS_PANCD_DATA.WS_DEC_LEN;
        BPCOQACD.DATA[BPCOQACD.CNT-1].SG_FLG = WS_PANCD_DATA.WS_SG_FLG;
        BPCOQACD.DATA[BPCOQACD.CNT-1].IPT_FLG = WS_PANCD_DATA.WS_FLD_IPT_FLG;
        BPCOQACD.DATA[BPCOQACD.CNT-1].PBCD_KND = WS_PANCD_DATA.WS_PBCD_TYPE;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, K_R_MBRW_PARM, BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            || BPCRMBPM.RETURN_INFO == 'L') {
            WS_STOP_FLG = 'Y';
        } else {
            if (BPCRMBPM.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOQACD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOQACD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOQACD = ");
            CEP.TRC(SCCGWA, BPCOQACD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
