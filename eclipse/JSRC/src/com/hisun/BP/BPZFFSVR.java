package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFFSVR {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZFFSVR";
    String CPN_F_MAINTAIN_PARM = "BP-F-F-MAINTAIN-PARM";
    String WS_ERR_MSG = "BP1001";
    char WS_TBL_FARM_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFPARM BPCFPARM = new BPCFPARM();
    BPVFSVR BPVFSVR = new BPVFSVR();
    SCCGWA SCCGWA;
    BPCGPFEE BPCGPFEE;
    BPCFFSVR BPCFFSVR;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFFSVR BPCFFSVR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFFSVR = BPCFFSVR;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFFSVR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCGPFEE = (BPCGPFEE) GWA_BP_AREA.FEE_AREA.FEE_PARM_PTR;
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
        BPCFFSVR.RC.RC_MMO = SCCGWA.COMM_AREA.AP_MMO;
        BPCFFSVR.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, BPCFFSVR.DATA.SVR_CODE);
        if (BPCFFSVR.DATA.SVR_CODE.trim().length() == 0 
            || BPCFFSVR.DATA.SVR_CODE.equalsIgnoreCase("0") 
            || BPCFFSVR.DATA.SVR_CODE.charAt(0) == 0X00) {
            BPCFFSVR.DATA.SVR_CODE = SCCGWA.COMM_AREA.SERV_CODE;
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        IBS.init(SCCGWA, BPVFSVR);
        BPVFSVR.KEY.SVR_NO = BPCFFSVR.DATA.SVR_CODE;
        CEP.TRC(SCCGWA, BPVFSVR.KEY.SVR_NO);
        BPCFPARM.INFO.POINTER = BPVFSVR;
        BPCFPARM.INFO.FUNC = '3';
        BPCFPARM.INFO.TYPE = "FSVR ";
        S000_CALL_BPZFPARM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        BPCGPFEE.SVR_DATA.AUT_FLG = BPVFSVR.VAL.AUT_FLG;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
        CEP.TRC(SCCGWA, BPCGPFEE.TX_DATA.CHG_AC_INFO.AC_TYP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZFPARM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_MAINTAIN_PARM, BPCFPARM);
        if (BPCFPARM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFPARM.RC);
            if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFPARM.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFFSVR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFFSVR = ");
            CEP.TRC(SCCGWA, BPCFFSVR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
