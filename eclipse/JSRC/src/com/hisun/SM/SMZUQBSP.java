package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCWOUT;
import com.hisun.DB.*;

import java.io.IOException;
import java.sql.SQLException;

public class SMZUQBSP {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    SMZUQBSP_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new SMZUQBSP_WS_TEMP_VARIABLE();
    char WS_WF_FLG = ' ';
    SMCMSG_ERROR_MSG SMCMSG_ERROR_MSG = new SMCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCRWSPC SCCRWSPC = new SCCRWSPC();
    SCRBSPC SCRBSPC = new SCRBSPC();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SMCC11 SMCC11 = new SMCC11();
    DB2RTCD DB2RTCD = new DB2RTCD();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SMCUQBSP SMCUQBSP;
    public void MP(SCCGWA SCCGWA, SMCUQBSP SMCUQBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SMCUQBSP = SMCUQBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SMZUQBSP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCRWSPC);
        SCCRWSPC.PTR = SCRBSPC;
        SCCRWSPC.LEN = 382;
        SMCUQBSP.RC.RC_MMO = "SM";
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_INQ_BSP_REC();
        if (pgmRtn) return;
    }
    public void B001_INQ_BSP_REC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCRBSPC);
        SCRBSPC.KEY.SERV_CODE = SMCUQBSP.SERV_CODE;
        SCCRWSPC.FUNC = 'Q';
        S000_CALL_SCZRWSPC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SMCC11);
        IBS.init(SCCGWA, SCCFMT);
        SMCC11.SERV_CODE = SCRBSPC.KEY.SERV_CODE;
        SMCC11.REMARK = SCRBSPC.REMARK;
        SMCC11.TABLE_NAME = SCRBSPC.TABLE_NAME;
        SMCC11.UCMP_NAME = SCRBSPC.UCMP_NAME;
        SMCC11.BSP_TYPE = SCRBSPC.BSP_TYPE;
        SMCC11.BSP_OPER_STS = SCRBSPC.BSP_OPER_STS;
        SMCC11.WAIT_SERV_CODE = SCRBSPC.WAIT_SERV_CODE;
        SMCC11.REVERSE_FLG = SCRBSPC.REVERSE_FLG;
        SMCC11.ERROR_FLG = SCRBSPC.ERROR_FLG;
        SMCC11.RUN_TYPE = SCRBSPC.RUN_TYPE;
        if (SCRBSPC.RUN_TYPE == 'T') {
            SMCC11.TR_CODE = SCRBSPC.RUN_CMP_NAME;
        } else {
            SMCC11.RUN_CMP_NAME = SCRBSPC.RUN_CMP_NAME;
        }
        SMCC11.RV_CMP_NAME = SCRBSPC.RV_CMP_NAME;
        SMCC11.GEN_PROC = SCRBSPC.GEN_PROC;
        SMCC11.BSP_PROC = SCRBSPC.BSP_PROC;
        SMCC11.BAT_PROC = SCRBSPC.BAT_PROC;
        SCCFMT.FMTID = "SMX01";
        SCCFMT.DATA_PTR = SMCC11;
        SCCFMT.DATA_LEN = 364;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_SCZRWSPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-R-UPD-BSP-CTL", SCCRWSPC);
        if (SCCRWSPC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCRWSPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], SMCUQBSP.RC);
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
