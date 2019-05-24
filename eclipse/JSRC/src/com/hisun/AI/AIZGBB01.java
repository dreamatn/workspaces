package com.hisun.AI;

import com.hisun.BP.*;
import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class AIZGBB01 {
    DBParm AITBB01_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_I = 0;
    int WS_J = 0;
    String WS_MSG_ERR = " ";
    short WS_FLD_NO = 0;
    char WS_FND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    AIRBB01 AIRBB01 = new AIRBB01();
    SCCGWA SCCGWA;
    AICGBB01 AICGBB01;
    public void MP(SCCGWA SCCGWA, AICGBB01 AICGBB01) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.AICGBB01 = AICGBB01;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AIZGBB01 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        AICGBB01.FOUND_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (AICGBB01.FUNC == 'I') {
            B020_QUERY_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_IPT_FUNC_ERR);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, AICGBB01.FUNC);
        CEP.TRC(SCCGWA, AICGBB01.PRDT_CODE);
        CEP.TRC(SCCGWA, AICGBB01.FOUND_FLG);
        if (AICGBB01.PRDT_CODE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_PRD_CODE_MUSTINPUT);
        }
    }
    public void B020_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRBB01);
        CEP.TRC(SCCGWA, AIRBB01.PROD_TYPE);
        AIRBB01.PROD_TYPE = AICGBB01.PRDT_CODE;
        CEP.TRC(SCCGWA, AIRBB01.PROD_TYPE);
        T000_READ_AITBB01_FIRST();
        if (pgmRtn) return;
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ERR);
        Z_RET();
        if (pgmRtn) return;
    }
    public void T000_READ_AITBB01_FIRST() throws IOException,SQLException,Exception {
        AITBB01_RD = new DBParm();
        AITBB01_RD.TableName = "AITBB01";
        AITBB01_RD.where = "PROD_TYPE = :AIRBB01.PROD_TYPE "
            + "AND ( BAL1 < > 0 "
            + "OR BAL2 < > 0 "
            + "OR BAL3 < > 0 "
            + "OR BAL4 < > 0 "
            + "OR BAL5 < > 0 "
            + "OR BAL6 < > 0 "
            + "OR BAL7 < > 0 "
            + "OR BAL8 < > 0 "
            + "OR BAL9 < > 0 "
            + "OR BAL10 < > 0 "
            + "OR BAL11 < > 0 "
            + "OR BAL12 < > 0 "
            + "OR BAL13 < > 0 "
            + "OR BAL14 < > 0 "
            + "OR BAL15 < > 0 "
            + "OR BAL16 < > 0 "
            + "OR BAL17 < > 0 "
            + "OR BAL18 < > 0 "
            + "OR BAL19 < > 0 "
            + "OR BAL20 < > 0 "
            + "OR BAL21 < > 0 "
            + "OR BAL22 < > 0 "
            + "OR BAL23 < > 0 "
            + "OR BAL24 < > 0 "
            + "OR BAL25 < > 0 "
            + "OR BAL26 < > 0 "
            + "OR BAL27 < > 0 "
            + "OR BAL28 < > 0 "
            + "OR BAL29 < > 0 "
            + "OR BAL30 < > 0 "
            + "OR BAL31 < > 0 "
            + "OR BAL32 < > 0 "
            + "OR BAL33 < > 0 "
            + "OR BAL34 < > 0 "
            + "OR BAL35 < > 0 "
            + "OR BAL36 < > 0 "
            + "OR BAL37 < > 0 "
            + "OR BAL38 < > 0 "
            + "OR BAL39 < > 0 "
            + "OR BAL40 < > 0 "
            + "OR BAL41 < > 0 "
            + "OR BAL42 < > 0 "
            + "OR BAL43 < > 0 "
            + "OR BAL44 < > 0 "
            + "OR BAL45 < > 0 "
            + "OR BAL46 < > 0 "
            + "OR BAL47 < > 0 "
            + "OR BAL48 < > 0 "
            + "OR BAL49 < > 0 "
            + "OR BAL50 < > 0 "
            + "OR BAL51 < > 0 "
            + "OR BAL52 < > 0 "
            + "OR BAL53 < > 0 "
            + "OR BAL54 < > 0 "
            + "OR BAL55 < > 0 "
            + "OR BAL56 < > 0 "
            + "OR BAL57 < > 0 "
            + "OR BAL58 < > 0 "
            + "OR BAL59 < > 0 "
            + "OR BAL60 < > 0 "
            + "OR BAL61 < > 0 "
            + "OR BAL62 < > 0 "
            + "OR BAL63 < > 0 "
            + "OR BAL64 < > 0 "
            + "OR BAL65 < > 0 "
            + "OR BAL66 < > 0 "
            + "OR BAL67 < > 0 "
            + "OR BAL68 < > 0 "
            + "OR BAL69 < > 0 "
            + "OR BAL70 < > 0 "
            + "OR BAL71 < > 0 "
            + "OR BAL72 < > 0 "
            + "OR BAL73 < > 0 "
            + "OR BAL74 < > 0 "
            + "OR BAL75 < > 0 "
            + "OR BAL76 < > 0 )";
        AITBB01_RD.fst = true;
        IBS.READ(SCCGWA, AIRBB01, this, AITBB01_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, AICGBB01.FOUND_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            AICGBB01.FOUND_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            AICGBB01.FOUND_FLG = 'N';
        } else {
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
