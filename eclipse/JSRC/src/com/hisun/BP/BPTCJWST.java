package com.hisun.BP;

import com.hisun.PS.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPTCJWST {
    String JIBS_tmp_str[] = new String[10];
    brParm PSTOBLL_BR = new brParm();
    String WS_ERR_MSG = " ";
    BPTCJWST_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPTCJWST_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    PSROBLL PSROBLL = new PSROBLL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCCJWST BPCCJWST;
    public void MP(SCCGWA SCCGWA, BPCCJWST BPCCJWST) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCCJWST = BPCCJWST;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPTCJWST return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_READ_PSTOBLL();
    }
    public void B010_READ_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.SHL_EXG_DT = SCCGWA.COMM_AREA.AC_DATE;
        PSROBLL.EXG_DC = 'D';
        PSROBLL.EXG_TX_BR = BPCCJWST.BR;
        CEP.TRC(SCCGWA, BPCCJWST.BR);
        S000_STARTBR_PSTOBLL();
        S000_READNEXT_PSTOBLL();
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_TEMP_VARIABLE.WS_I != 1) {
            if (PSROBLL.EXG_REPT_FLG == '0') {
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                CEP.TRC(SCCGWA, WS_ERR_MSG);
                if ("1".trim().length() == 0) WS_TEMP_VARIABLE.WS_I = 0;
                else WS_TEMP_VARIABLE.WS_I = Integer.parseInt("1");
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
                if (JIBS_tmp_str[0].equalsIgnoreCase("9994610")) {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.NF_NOT_ACQU;
                } else {
                    WS_ERR_MSG = PSCMSG_ERROR_MSG.NF_NOT_ACQ;
                }
                S000_ERR_MSG_PROC();
            }
            S000_READNEXT_PSTOBLL();
        }
        S000_ENDBR_PSTOBLL();
    }
    public void S000_STARTBR_PSTOBLL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PSROBLL.SHL_EXG_DT);
        CEP.TRC(SCCGWA, PSROBLL.EXG_DC);
        PSTOBLL_BR.rp = new DBParm();
        PSTOBLL_BR.rp.TableName = "PSTOBLL";
        PSTOBLL_BR.rp.where = "SHL_EXG_DT = :PSROBLL.SHL_EXG_DT "
            + "AND EXG_DC = :PSROBLL.EXG_DC "
            + "AND EXG_TX_BR = :PSROBLL.EXG_TX_BR";
        IBS.STARTBR(SCCGWA, PSROBLL, this, PSTOBLL_BR);
    }
    public void S000_READNEXT_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSROBLL, this, PSTOBLL_BR);
    }
    public void S000_ENDBR_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTOBLL_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
