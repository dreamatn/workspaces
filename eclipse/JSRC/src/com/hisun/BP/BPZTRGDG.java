package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTRGDG {
    int JIBS_tmp_int;
    DBParm BPTRGND_RD;
    String PGM_BPZTRGDG = "BPZTRGDG";
    char WS_TBL_ORG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRRGND BPRRGND = new BPRRGND();
    int WS_TEMP_CNT = 0;
    SCCGWA SCCGWA;
    BPCRRGDG BPCRRGDG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRRGDG BPCRRGDG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRRGDG = BPCRRGDG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZTRGDG return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GROUP_RECORDS_PROC();
    }
    public void B010_GROUP_RECORDS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRRGND);
        BPRRGND.KEY.BNK = BPCRRGDG.INFO.BNK;
        BPRRGND.KEY.RGN_TYPE = BPCRRGDG.INFO.RGN_TYPE;
        BPRRGND.KEY.RGN_UNIT = "" + BPCRRGDG.INFO.BR;
        JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT = "0" + BPRRGND.KEY.RGN_UNIT;
        CEP.TRC(SCCGWA, "AAAAAAAA");
        T000_GROUP_BPTRGND();
        CEP.TRC(SCCGWA, WS_TEMP_CNT);
        BPCRRGDG.RETURN_CNT = WS_TEMP_CNT;
    }
    public void T000_GROUP_BPTRGND() throws IOException,SQLException,Exception {
        BPTRGND_RD = new DBParm();
        BPTRGND_RD.TableName = "BPTRGND";
        BPTRGND_RD.set = "WS-TEMP-CNT=COUNT(*)";
        BPTRGND_RD.where = "BNK = :BPRRGND.KEY.BNK "
            + "AND RGN_TYPE = :BPRRGND.KEY.RGN_TYPE "
            + "AND RGN_UNIT = :BPRRGND.KEY.RGN_UNIT";
        IBS.GROUP(SCCGWA, BPRRGND, this, BPTRGND_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRRGDG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRRGDG = ");
            CEP.TRC(SCCGWA, BPCRRGDG);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
