package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZDACNO {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    BPCDACNO BPCDACNO;
    public void MP(SCCGWA SCCGWA, BPCDACNO BPCDACNO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCDACNO = BPCDACNO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_DIFF_PROCESS();
        CEP.TRC(SCCGWA, BPCDACNO.OUR_OR_OTHER);
        CEP.TRC(SCCGWA, "BPZDACNO return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCDACNO.AC);
    }
    public void B000_DIFF_PROCESS() throws IOException,SQLException,Exception {
        if (BPCDACNO.AC.trim().length() > 0) {
            if (BPCDACNO.AC == null) BPCDACNO.AC = "";
            JIBS_tmp_int = BPCDACNO.AC.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) BPCDACNO.AC += " ";
            if (BPCDACNO.AC.substring(0, 3).equalsIgnoreCase("999")) {
                BPCDACNO.OUR_OR_OTHER = "OTHER";
            } else {
                BPCDACNO.OUR_OR_OTHER = "OUR";
            }
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCDACNO.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCDACNO.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCDACNO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCDACNO = ");
            CEP.TRC(SCCGWA, BPCDACNO);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
