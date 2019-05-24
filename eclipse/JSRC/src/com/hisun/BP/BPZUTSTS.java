package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUTSTS {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCUFHIS BPCUFHIS = new BPCUFHIS();
    BPRFHIST BPRFHIST = new BPRFHIST();
    SCCGWA SCCGWA;
    BPCUTSTS BPCUTSTS;
    public void MP(SCCGWA SCCGWA, BPCUTSTS BPCUTSTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUTSTS = BPCUTSTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUTSTS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUTSTS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUFHIS);
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCUTSTS.AC_DT;
        BPRFHIST.KEY.JRNNO = BPCUTSTS.JRNNO;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '3';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '4';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        WS_I = 0;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        while (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            BPRFHIST.PRINT_FLG = BPCUTSTS.STS;
            CEP.TRC(SCCGWA, BPRFHIST);
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            BPCUFHIS.DATA.FUNC = '2';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
            WS_I += 1;
            BPCUFHIS.DATA.POINTER = BPRFHIST;
            BPCUFHIS.DATA.REC_LEN = 690;
            BPCUFHIS.DATA.FUNC = '4';
            S000_CALL_BPZUFHIS();
            if (pgmRtn) return;
        }
        BPCUFHIS.DATA.POINTER = BPRFHIST;
        BPCUFHIS.DATA.REC_LEN = 690;
        BPCUFHIS.DATA.FUNC = '5';
        S000_CALL_BPZUFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_I);
        BPCUTSTS.CNT = WS_I;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCUTSTS.RC);
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZUFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-UPD-FHIST", BPCUFHIS);
        CEP.TRC(SCCGWA, BPCUFHIS.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL) 
            && !JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCUFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCUTSTS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCUTSTS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCUTSTS = ");
            CEP.TRC(SCCGWA, BPCUTSTS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
