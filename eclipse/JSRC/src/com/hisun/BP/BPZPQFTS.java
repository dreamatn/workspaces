package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQFTS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_PARM_READ = "BP-PARM-READ        ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    BPCPQFTS BPCPQFTS;
    public void MP(SCCGWA SCCGWA, BPCPQFTS BPCPQFTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQFTS = BPCPQFTS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQFTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B10_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B20_GET_FLT_END_STS();
        if (pgmRtn) return;
    }
    public void B10_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B20_GET_FLT_END_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        BPRPRMT.KEY.TYP = "FLTEF";
        if (BPRPRMT.KEY.CD == null) BPRPRMT.KEY.CD = "";
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRMT.KEY.CD += " ";
        if (SCCGWA.COMM_AREA.TR_BANK == null) SCCGWA.COMM_AREA.TR_BANK = "";
        JIBS_tmp_int = SCCGWA.COMM_AREA.TR_BANK.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) SCCGWA.COMM_AREA.TR_BANK += " ";
        BPRPRMT.KEY.CD = SCCGWA.COMM_AREA.TR_BANK + BPRPRMT.KEY.CD.substring(3);
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FLT_END_STS_NOTFND, BPCPQFTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPRPRMT.DAT_TXT.FILLER == null) BPRPRMT.DAT_TXT.FILLER = "";
        JIBS_tmp_int = BPRPRMT.DAT_TXT.FILLER.length();
        for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPRMT.DAT_TXT.FILLER += " ";
        BPCPQFTS.END_STS = BPRPRMT.DAT_TXT.FILLER.substring(0, 1).charAt(0);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_PARM_READ, BPCPRMR);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (BPCPRMR.RC.RC_RTNCODE != 0 
            && !JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQFTS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQFTS = ");
            CEP.TRC(SCCGWA, BPCPQFTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
