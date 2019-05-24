package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZQCITY {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_CITY_TYPE = "CITY";
    String K_PGM_NAME = "BPZQCITY";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    char WS_FIND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRCITY BPRCITY = new BPRCITY();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPARM BPRPARM = new BPRPARM();
    SCCGWA SCCGWA;
    BPCQCITY BPCQCITY;
    public void MP(SCCGWA SCCGWA, BPCQCITY BPCQCITY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCQCITY = BPCQCITY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZQCITY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCITY);
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCQCITY.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_QUERY_CITY_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCQCITY.INPUT_DAT.CITY_CD.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CITY_CD_M_INPUT, BPCQCITY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_QUERY_CITY_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_CITY_TYPE;
        CEP.TRC(SCCGWA, BPRPARM.KEY.TYPE);
        BPCRMBPM.FUNC = 'S';
        BPCRMBPM.PTR = BPRPARM;
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        BPCRMBPM.FUNC = 'R';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        while (BPCRMBPM.RETURN_INFO != 'L' 
            && WS_FIND_FLG != 'Y') {
            if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
            JIBS_tmp_int = BPRPARM.KEY.CODE.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
            if (BPRPARM.KEY.CODE.substring(5 - 1, 5 + 4 - 1).equalsIgnoreCase(BPCQCITY.INPUT_DAT.CITY_CD)) {
                if (BPRPARM.KEY.CODE == null) BPRPARM.KEY.CODE = "";
                JIBS_tmp_int = BPRPARM.KEY.CODE.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPARM.KEY.CODE += " ";
                BPCQCITY.OUTPUT_DAT.CNTY_CD = BPRPARM.KEY.CODE.substring(0, 4);
                WS_FIND_FLG = 'Y';
            }
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCQCITY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCQCITY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCQCITY = ");
            CEP.TRC(SCCGWA, BPCQCITY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
